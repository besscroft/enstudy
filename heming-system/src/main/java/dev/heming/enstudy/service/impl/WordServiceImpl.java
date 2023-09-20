package dev.heming.enstudy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import dev.heming.enstudy.common.constant.CacheConstants;
import dev.heming.enstudy.common.converter.WordConverter;
import dev.heming.enstudy.common.entity.Book;
import dev.heming.enstudy.common.entity.Word;
import dev.heming.enstudy.common.param.word.WordAddParam;
import dev.heming.enstudy.common.param.word.WordUpdateParam;
import dev.heming.enstudy.common.vo.console.ConsoleVo;
import dev.heming.enstudy.common.vo.word.WordInfoVo;
import dev.heming.enstudy.mapper.BookDictMapper;
import dev.heming.enstudy.mapper.UserMapper;
import dev.heming.enstudy.mapper.UserWrongWordMapper;
import dev.heming.enstudy.mapper.WordMapper;
import dev.heming.enstudy.service.WordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @Description 单词服务实现类
 * @Author Bess Croft
 * @Date 2023/8/31 21:34
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WordServiceImpl extends ServiceImpl<WordMapper, Word> implements WordService {

    private final MongoTemplate mongoTemplate;
    private final UserMapper userMapper;
    private final BookDictMapper bookDictMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final WordConverter wordConverter;
    private final UserWrongWordMapper  userWrongWordMapper;

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
                            consoleVo.setWrongCount(userWrongWordMapper.selectCount(new QueryWrapper<>()));
                            redisTemplate.opsForValue().set(CacheConstants.CONSOLE_INFO, consoleVo, 24, TimeUnit.HOURS);
                            return consoleVo;
                        }
                );
    }

    @Override
    public Book getWordByWordIdAndBookId(Long wordId, String bookId) {
        String key = StringUtils.replace(CacheConstants.BOOK_WORD, "#{bookId}", bookId)
                .replace("#{wordId}", wordId.toString());
        return (Book) Optional.ofNullable(redisTemplate.opsForValue().get(key))
                .orElseGet(() -> {
                    Word word = this.baseMapper.selectById(wordId);
                    Assert.notNull(word, "单词数据不存在，请联系管理员！");
                    Query query = new Query(
                            Criteria
                                    .where("bookId").is(bookId)
                                    .and("wordRank").is(word.getWordRank())
                                    .and("headWord").is(word.getHeadWord())
                    );
                    Book book = mongoTemplate.findOne(query, Book.class);
                    redisTemplate.opsForValue().set(key, book);
                    return book;
                });
    }

    @Override
    @CacheEvict(value = {
            CacheConstants.CONSOLE_INFO
    }, allEntries = true)
    public void addWord(WordAddParam param) {
        Word word = wordConverter.AddParamToWord(param);
        Book book = mongoTemplate.insert(param.getWordJson());
        Assert.notNull(book.getId(), "新增单词失败！");
        word.setWordJsonId(book.getId());
        Assert.isTrue(this.baseMapper.insert(word) > 0, "新增单词失败！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateWord(WordUpdateParam param) {
        Word oldWord = this.baseMapper.selectById(param.getId());
        Assert.notNull(oldWord, "未找到要更新的数据！");
        Word word = wordConverter.UpdateParamToWord(param);
        Query query = new Query(
                Criteria
                        .where("bookId").is(oldWord.getBookId())
                        .and("wordRank").is(oldWord.getWordRank())
                        .and("headWord").is(oldWord.getHeadWord())
        );
        Book book = mongoTemplate.findOne(query, Book.class);
        Assert.notNull(book, "单词详情不存在！");
        Update update = new Update()
                .set("content", param.getWordJson().getContent())
                .set("wordRank", word.getWordRank())
                .set("headWord", word.getHeadWord())
                .set("bookId", word.getBookId());
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Book.class);
        Assert.isTrue(updateResult.getModifiedCount() > 0 && this.baseMapper.updateById(word) > 0, "更新单词失败！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteWord(Long wordId) {
        Word word = this.baseMapper.selectById(wordId);
        Query query = new Query(
                Criteria
                        .where("bookId").is(word.getBookId())
                        .and("wordRank").is(word.getWordRank())
                        .and("headWord").is(word.getHeadWord())
        );
        DeleteResult deleteResult = mongoTemplate.remove(query, Book.class);
        Assert.isTrue(deleteResult.getDeletedCount() > 0 && this.baseMapper.deleteById(wordId) > 0, "删除失败！");
    }

    @Override
    @Cacheable(value = CacheConstants.WORD_INFO, key = "#wordId", unless = "#result == null")
    public WordInfoVo getWordById(Long wordId) {
        Word word = this.baseMapper.selectById(wordId);
        Assert.notNull(word, "单词数据不存在！");
        Query query = new Query(
                Criteria
                        .where("bookId").is(word.getBookId())
                        .and("wordRank").is(word.getWordRank())
                        .and("headWord").is(word.getHeadWord())
        );
        Book book = mongoTemplate.findOne(query, Book.class);
        Assert.notNull(book, "单词数据不存在！");
        WordInfoVo vo = new WordInfoVo();
        BeanUtils.copyProperties(word, vo);
        vo.setWordJson(book);
        return vo;
    }

}
