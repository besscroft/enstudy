package dev.heming.enstudy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.heming.enstudy.common.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 用户 Mapper 接口
 * @Author Bess Croft
 * @Date 2023/8/24 23:03
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    User selectByUsername(@Param("username") String username);

    /**
     * 列表查询
     * @param role 角色标识
     * @return 用户列表
     */
    List<User> selectPageList(@Param("role") String role);

    /**
     * 查询用户的密码
     * @param id 用户 id
     * @return 用户密码
     */
    String selectPasswordById(@Param("id") Long id);

    /**
     * 更新用户密码
     * @param id 用户 id
     * @param password 加密后的密码
     * @return 变更行数
     */
    int updatePasswordById(@Param("id") Long id,
                           @Param("password") String password);

    /**
     * 查询用户总数
     * @return 用户总数
     */
    Integer selectUserCount();

}
