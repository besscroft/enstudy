package dev.heming.enstudy.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import dev.heming.enstudy.common.entity.User;
import dev.heming.enstudy.common.param.user.UserAddParam;
import dev.heming.enstudy.common.param.user.UserUpdateParam;

import java.util.List;
import java.util.Map;

/**
 * @Description 用户 服务
 * @Author Bess Croft
 * @Date 2023/8/24 23:04
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return Token 相关参数
     */
    SaTokenInfo login(String username, String password);

    /**
     * 获取已登录用户信息
     * @return 组装的用户信息
     */
    Map<String, Object> info();

    /**
     * 获取用户
     * @param id 用户 id
     * @return 用户实体
     */
    User getUserById(Long id);

    /**
     * 用户分页列表
     * @param pageNum 页码
     * @param pageSize 页大小
     * @param role 角色标识
     * @return 用户分页列表数据
     */
    List<User> userPage(Integer pageNum, Integer pageSize, String role);

    /**
     * 用户删除
     * @param userId 用户 id
     */
    void deleteUser(Long userId);

    /**
     * 获取用户信息
     * @param username 用户名
     * @return 用户实体
     */
    User getUser(String username);

    /**
     * 新增用户
     * @param param 请求参数
     */
    void addUser(UserAddParam param);

    /**
     * 更新用户信息
     * @param param 请求参数
     */
    void updateUser(UserUpdateParam param);

    /**
     * 更新用户状态
     * @param id 用户 id
     * @param status 状态
     */
    void updateStatus(Long id, Integer status);

    /**
     * 更新用户密码
     * @param userId 用户 id
     * @param isSelf 是否是自己
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void updatePassword(Long userId, Boolean isSelf, String oldPassword, String newPassword);

    /**
     * 退出登录
     */
    void logout();

}
