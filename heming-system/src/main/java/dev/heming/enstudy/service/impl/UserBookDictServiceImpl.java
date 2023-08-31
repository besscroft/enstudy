package dev.heming.enstudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.heming.enstudy.common.entity.UserBookDict;
import dev.heming.enstudy.mapper.UserBookDictMapper;
import dev.heming.enstudy.service.UserBookDictService;
import org.springframework.stereotype.Service;

/**
 * @Description 用户活动词库 服务实现类
 * @Author Bess Croft
 * @Date 2023/8/31 21:37
 */
@Service
public class UserBookDictServiceImpl extends ServiceImpl<UserBookDictMapper, UserBookDict> implements UserBookDictService {
}
