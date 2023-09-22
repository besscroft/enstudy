package dev.heming.enstudy.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import dev.heming.enstudy.common.constant.CacheConstants;
import dev.heming.enstudy.common.constant.RoleConstants;
import dev.heming.enstudy.common.constant.SystemConstants;
import dev.heming.enstudy.common.converter.UserConverter;
import dev.heming.enstudy.common.entity.User;
import dev.heming.enstudy.common.exception.HeMingFileException;
import dev.heming.enstudy.common.param.user.UserAddParam;
import dev.heming.enstudy.common.param.user.UserUpdateParam;
import dev.heming.enstudy.mapper.UserMapper;
import dev.heming.enstudy.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description 用户 Service 实现类
 * @Author Bess Croft
 * @Date 2023/8/24 23:04
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserConverter userConverter;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public SaTokenInfo login(String username, String password) {
        ServletUriComponentsBuilder request = ServletUriComponentsBuilder.fromCurrentRequest();
        log.info("用户发起登录请求:{}，请求 uri 为：{}", username, request.toUriString());
        User user = this.baseMapper.selectByUsername(username);
        Assert.notNull(user, "账号或密码错误！");
        if (Objects.equals(user.getStatus(), SystemConstants.STATUS_NO)) {
            throw new HeMingFileException(String.format("账号：%s 已被禁用，请联系管理员！", username));
        }
        if (!Objects.equals(SaSecureUtil.sha256(password), user.getPassword())) {
            throw new HeMingFileException("账号或密码错误！");
        }
        // 登录
        StpUtil.login(user.getId());
        // 设置最后登录时间
        user.setLoginTime(LocalDateTime.now());
        this.updateById(user);
        return StpUtil.getTokenInfo();
    }

    @Override
    public Map<String, Object> info() {
        long userId = StpUtil.getLoginIdAsLong();
        return (Map<String, Object>) Optional.ofNullable(redisTemplate.opsForValue().get(CacheConstants.USER_INFO_ID + userId))
                .orElseGet(() -> {
                    User user = getUserById(userId);
                    Assert.notNull(user, "暂未登录！");
                    Map<String, Object> map = Map.of(
                            "userName", Optional.ofNullable(user.getName()).orElse(""),
                            "avatar", Optional.ofNullable(user.getAvatar()).orElse(""),
                            "role", Optional.ofNullable(user.getRole()).orElse(""),
                            "email", Optional.ofNullable(user.getEmail()).orElse("")
                    );
                    return map;
                });
    }

    @Override
    public User getUserById(Long userId) {
        return (User) Optional.ofNullable(redisTemplate.opsForValue().get(CacheConstants.USER_ID + userId)).orElseGet(() -> {
            long currentUserId = StpUtil.getLoginIdAsLong();
            if (!Objects.equals(userId, currentUserId)) {
                StpUtil.checkRoleOr(RoleConstants.PLATFORM_SUPER_ADMIN, RoleConstants.PLATFORM_ADMIN);
            }
            User user = this.baseMapper.selectById(userId);
            redisTemplate.opsForValue().set(CacheConstants.USER_ID + userId, user, 24, TimeUnit.HOURS);
            return user;
        });
    }

    @Override
    public List<User> userPage(Integer pageNum, Integer pageSize, String role) {
        PageHelper.startPage(pageNum, pageSize);
        return this.baseMapper.selectPageList(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Long userId) {
        User user = getUserById(userId);
        Assert.notNull(user, "用户不存在！");
        if (Objects.equals(user.getRole(), RoleConstants.PLATFORM_SUPER_ADMIN)) {
            throw new HeMingFileException("超级管理员不允许被删除！");
        }
        Assert.isTrue(this.baseMapper.deleteById(userId) > 0, "用户删除失败！");
        redisTemplate.delete(CacheConstants.USER_NAME + user.getUsername());
        redisTemplate.delete(CacheConstants.USER_INFO_ID + userId);
        redisTemplate.delete(CacheConstants.USER_ID + userId);
        redisTemplate.delete(CacheConstants.CONSOLE_INFO);
    }

    @Override
    public User getUser(String username) {
        return (User) Optional.ofNullable(redisTemplate.opsForValue().get(CacheConstants.USER_NAME + username)).orElseGet(() -> {
            User user = this.baseMapper.selectByUsername(username);
            redisTemplate.opsForValue().set(CacheConstants.USER_NAME + username, user, 24, TimeUnit.HOURS);
            return user;
        });
    }

    @Override
    public void addUser(UserAddParam param) {
        User user = userConverter.AddParamToUser(param);
        if (Objects.equals(user.getRole(), RoleConstants.PLATFORM_SUPER_ADMIN)) {
            throw new HeMingFileException("违反规则！超级管理员角色不允许被添加！");
        }
        user.setStatus(SystemConstants.STATUS_NO);
        user.setPassword(SaSecureUtil.sha256(param.getPassword().trim()));
        Assert.isTrue(this.baseMapper.insert(user) > 0, "新增用户失败！");
        redisTemplate.delete(CacheConstants.CONSOLE_INFO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserUpdateParam param) {
        User user = userConverter.UpdateParamToUser(param);
        User oldUser = this.baseMapper.selectById(user.getId());
        // 如果原来不是超级管理员，现在是超级管理员，或者原来是超级管理员，现在不是超级管理员，抛出异常
        if ((!Objects.equals(oldUser.getRole(), RoleConstants.PLATFORM_SUPER_ADMIN) && Objects.equals(user.getRole(), RoleConstants.PLATFORM_SUPER_ADMIN))
                || (Objects.equals(oldUser.getRole(), RoleConstants.PLATFORM_SUPER_ADMIN) && !Objects.equals(user.getRole(), RoleConstants.PLATFORM_SUPER_ADMIN))) {
            throw new HeMingFileException("违反规则！更新用户失败！");
        }
        // 非管理员只能修改自己的信息
        if (!(Objects.equals(oldUser.getRole(), RoleConstants.PLATFORM_SUPER_ADMIN) || Objects.equals(oldUser.getRole(), RoleConstants.PLATFORM_ADMIN))
                && !Objects.equals(oldUser.getId(), user.getId())) {
            throw new HeMingFileException("违反规则！更新用户失败！");
        }
        Assert.isTrue(this.baseMapper.updateById(user) > 0, "更新用户失败！");
        redisTemplate.delete(CacheConstants.USER_NAME + user.getUsername());
        redisTemplate.delete(CacheConstants.USER_INFO_ID + user.getId());
        redisTemplate.delete(CacheConstants.USER_ID + user.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(Long id, Integer status) {
        User user = this.baseMapper.selectById(id);
        Assert.notNull(user, "用户不存在！");
        if (Objects.equals(user.getRole(), RoleConstants.PLATFORM_SUPER_ADMIN)) {
            throw new HeMingFileException("超级管理员不允许被禁用！");
        }
        user.setStatus(status);
        Assert.isTrue(this.baseMapper.updateById(user) > 0, "更新用户状态失败！");
        redisTemplate.delete(CacheConstants.USER_NAME + user.getUsername());
        redisTemplate.delete(CacheConstants.USER_INFO_ID + user.getId());
        redisTemplate.delete(CacheConstants.USER_ID + user.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(Long userId, Boolean isSelf, String oldPassword, String newPassword) {
        if (Boolean.TRUE.equals(isSelf)) {
            // 如果是自己要修改密码，那么就从上下文中获取用户 id
            userId = StpUtil.getLoginIdAsLong();
        }
        String password = this.baseMapper.selectPasswordById(userId);
        if (!Objects.equals(SaSecureUtil.sha256(oldPassword), password)) {
            throw new HeMingFileException("旧密码错误！");
        }
        String sha256Pwd = SaSecureUtil.sha256(newPassword);
        this.baseMapper.updatePasswordById(userId, sha256Pwd);
    }

    @Override
    public void logout() {
        long userId = StpUtil.getLoginIdAsLong();
        User user = getUserById(userId);
        redisTemplate.delete(CacheConstants.USER_NAME + user.getUsername());
        redisTemplate.delete(CacheConstants.USER_INFO_ID + userId);
        redisTemplate.delete(CacheConstants.USER_ID + userId);
        StpUtil.logout();
    }

}
