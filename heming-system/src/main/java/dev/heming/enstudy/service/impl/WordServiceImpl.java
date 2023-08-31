package dev.heming.enstudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.heming.enstudy.common.entity.Word;
import dev.heming.enstudy.mapper.WordMapper;
import dev.heming.enstudy.service.WordService;
import org.springframework.stereotype.Service;

/**
 * @Description 单词服务实现类
 * @Author Bess Croft
 * @Date 2023/8/31 21:34
 */
@Service
public class WordServiceImpl extends ServiceImpl<WordMapper, Word> implements WordService {
}
