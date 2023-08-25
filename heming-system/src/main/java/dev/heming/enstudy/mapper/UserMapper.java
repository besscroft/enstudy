package dev.heming.enstudy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.heming.enstudy.common.entity.User;
import org.apache.ibatis.annotations.Param;

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

}
