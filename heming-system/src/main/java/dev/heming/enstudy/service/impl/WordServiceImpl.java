package dev.heming.enstudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import dev.heming.enstudy.common.constant.CacheConstants;
import dev.heming.enstudy.common.entity.Word;
import dev.heming.enstudy.common.vo.console.ConsoleVo;
import dev.heming.enstudy.mapper.BookDictMapper;
import dev.heming.enstudy.mapper.UserMapper;
import dev.heming.enstudy.mapper.WordMapper;
import dev.heming.enstudy.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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

}
