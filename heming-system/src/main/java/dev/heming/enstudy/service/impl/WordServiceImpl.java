package dev.heming.enstudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import dev.heming.enstudy.common.constant.CacheConstants;
import dev.heming.enstudy.common.entity.Word;
import dev.heming.enstudy.mapper.WordMapper;
import dev.heming.enstudy.service.WordService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 单词服务实现类
 * @Author Bess Croft
 * @Date 2023/8/31 21:34
 */
@Service
public class WordServiceImpl extends ServiceImpl<WordMapper, Word> implements WordService {

    @Override
    @Cacheable(value = CacheConstants.WORD_LIST, key = "#bookId", unless = "#result == null")
    public List<Word> getWordListByBookId(String bookId) {
        return this.baseMapper.selectAllByBookId(bookId);
    }

    @Override
    public List<Word> wordPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return this.list();
    }

}
