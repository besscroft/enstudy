package dev.heming.enstudy.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.heming.enstudy.common.constant.CacheConstants;
import dev.heming.enstudy.common.constant.SystemConstants;
import dev.heming.enstudy.common.entity.User;
import dev.heming.enstudy.common.exception.HeMingFileException;
import dev.heming.enstudy.mapper.UserMapper;
import dev.heming.enstudy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * @Description 用户 Service 实现类
 * @Author Bess Croft
 * @Date 2023/8/24 23:04
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

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
        User user = getUserById(userId);
        Assert.notNull(user, "暂未登录！");
        Map<String, Object> map = new HashMap<>();
        map.put("userName", user.getName());
        map.put("avatar", user.getAvatar());
        map.put("role", user.getRole());
        return map;
    }

    @Override
    @Cacheable(value = CacheConstants.USER, key = "#userId", unless = "#result == null")
    public User getUserById(Long userId) {
        log.info("查询用户信息：{}", userId);
        return this.baseMapper.selectById(userId);
    }

}
