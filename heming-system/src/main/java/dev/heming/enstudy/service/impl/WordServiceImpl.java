package dev.heming.enstudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import dev.heming.enstudy.common.constant.CacheConstants;
import dev.heming.enstudy.common.converter.WordConverterMapper;
import dev.heming.enstudy.common.entity.Book;
import dev.heming.enstudy.common.entity.Word;
import dev.heming.enstudy.common.param.word.WordAddParam;
import dev.heming.enstudy.common.param.word.WordUpdateParam;
import dev.heming.enstudy.common.vo.console.ConsoleVo;
import dev.heming.enstudy.mapper.BookDictMapper;
import dev.heming.enstudy.mapper.UserMapper;
import dev.heming.enstudy.mapper.WordMapper;
import dev.heming.enstudy.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @Description 单词服务实现类
 * @Author Bess Croft
 * @Date 2023/8/31 21:34
 */
@Service
@RequiredArgsConstructor
public class WordServiceImpl extends ServiceImpl<WordMapper, Word> implements WordService {

    private final MongoTemplate mongoTemplate;
    private final UserMapper userMapper;
    private final BookDictMapper bookDictMapper;
    private final RedisTemplate<String, Object> redisTemplate;

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

    @Override
    public ConsoleVo getConsoleInfo() {
        return (ConsoleVo) Optional.ofNullable(redisTemplate.opsForValue().get(CacheConstants.CONSOLE_INFO))
                .orElseGet(
                        () -> {
                            ConsoleVo consoleVo = new ConsoleVo();
                            consoleVo.setDictCount(bookDictMapper.selectDictCount());
                            consoleVo.setWordCount(this.baseMapper.selectWordCount());
                            consoleVo.setUserCount(userMapper.selectUserCount());
                            redisTemplate.opsForValue().set(CacheConstants.CONSOLE_INFO, consoleVo, 24, TimeUnit.HOURS);
                            return consoleVo;
                        }
                );
    }

    @Override
    public Book getWordByWordIdAndBookId(Long wordId, String bookId) {
        // TODO 优化
        Word word = this.baseMapper.selectById(wordId);
        Assert.notNull(word, "单词数据不存在，请联系管理员！");
        Query query = new Query(
                Criteria
                        .where("bookId").is(bookId)
                        .and("wordRank").is(word.getWordRank())
                        .and("headWord").is(word.getHeadWord())
        );
        return mongoTemplate.findOne(query, Book.class);
    }

    @Override
    public void addWord(WordAddParam param) {
        Word word = WordConverterMapper.INSTANCE.AddParamToWord(param);
        // TODO mongo 新增数据
        Assert.isTrue(this.baseMapper.insert(word) > 0, "新增单词失败！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateWord(WordUpdateParam param) {
        Word oldWord = this.baseMapper.selectById(param.getId());
        Assert.notNull(oldWord, "未找到要更新的数据！");
        Word word = WordConverterMapper.INSTANCE.UpdateParamToWord(param);
        // TODO 更新 mongo 数据
        Assert.isTrue(this.baseMapper.updateById(word) > 0, "更新单词失败！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteWord(Long wordId) {
        // TODO 删除 mongo 数据
        Assert.isTrue(this.baseMapper.deleteById(wordId) > 0, "词典单词失败！");
    }

}
