package dev.heming.enstudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.heming.enstudy.common.entity.UserWrongWord;
import dev.heming.enstudy.mapper.UserWrongWordMapper;
import dev.heming.enstudy.service.UserWrongWordService;
import org.springframework.stereotype.Service;

/**
 * @Description 用户错题本 服务实现类
 * @Author Bess Croft
 * @Date 2023/8/31 21:35
 */
@Service
public class UserWrongWordServiceImpl extends ServiceImpl<UserWrongWordMapper, UserWrongWord> implements UserWrongWordService {
}
