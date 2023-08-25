package dev.heming.enstudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.heming.enstudy.common.User;
import dev.heming.enstudy.mapper.UserMapper;
import dev.heming.enstudy.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Description 用户 Service 实现类
 * @Author Bess Croft
 * @Date 2023/8/24 23:04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
