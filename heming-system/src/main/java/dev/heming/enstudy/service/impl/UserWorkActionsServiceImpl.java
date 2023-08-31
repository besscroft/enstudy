package dev.heming.enstudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.heming.enstudy.common.entity.UserWorkActions;
import dev.heming.enstudy.mapper.UserWorkActionsMapper;
import dev.heming.enstudy.service.UserWorkActionsService;
import org.springframework.stereotype.Service;

/**
 * @Description 用户学习行为 服务实现类
 * @Author Bess Croft
 * @Date 2023/8/31 21:36
 */
@Service
public class UserWorkActionsServiceImpl extends ServiceImpl<UserWorkActionsMapper, UserWorkActions> implements UserWorkActionsService {
}
