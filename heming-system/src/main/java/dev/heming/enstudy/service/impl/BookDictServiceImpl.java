package dev.heming.enstudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.heming.enstudy.common.entity.BookDict;
import dev.heming.enstudy.mapper.BookDictMapper;
import dev.heming.enstudy.service.BookDictService;
import org.springframework.stereotype.Service;

/**
 * @Description 词典服务实现类
 * @Author Bess Croft
 * @Date 2023/8/26 17:04
 */
@Service
public class BookDictServiceImpl extends ServiceImpl<BookDictMapper, BookDict> implements BookDictService {
}
