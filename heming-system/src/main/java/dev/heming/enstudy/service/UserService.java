package dev.heming.enstudy.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import dev.heming.enstudy.common.entity.User;

import java.util.Map;

/**
 * @Description 用户 Service
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

}
