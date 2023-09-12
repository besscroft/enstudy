package dev.heming.enstudy.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.heming.enstudy.common.constant.CacheConstants;
import dev.heming.enstudy.common.constant.SystemConstants;
import dev.heming.enstudy.common.converter.UserBookDictConverterMapper;
import dev.heming.enstudy.common.entity.BookDict;
import dev.heming.enstudy.common.entity.UserBookDict;
import dev.heming.enstudy.common.entity.UserWorkActions;
import dev.heming.enstudy.common.entity.Word;
import dev.heming.enstudy.common.param.dict.DictChoiceParam;
import dev.heming.enstudy.common.vo.statistics.TodayVo;
import dev.heming.enstudy.common.vo.userBookDict.UserBookDictVo;
import dev.heming.enstudy.common.vo.userWrongWord.UserWrongWordVo;
import dev.heming.enstudy.mapper.BookDictMapper;
import dev.heming.enstudy.mapper.UserBookDictMapper;
import dev.heming.enstudy.mapper.UserWorkActionsMapper;
import dev.heming.enstudy.mapper.UserWrongWordMapper;
import dev.heming.enstudy.service.UserBookDictService;
import dev.heming.enstudy.service.UserWorkActionsService;
import dev.heming.enstudy.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @Description 用户活动词典 服务实现类
 * @Author Bess Croft
 * @Date 2023/8/31 21:37
 */
@Service
@RequiredArgsConstructor
public class UserBookDictServiceImpl extends ServiceImpl<UserBookDictMapper, UserBookDict> implements UserBookDictService {

    private final BookDictMapper bookDictMapper;
    private final WordService wordService;
    private final UserWorkActionsService userWorkActionsService;
    private final UserWrongWordMapper userWrongWordMapper;
    private final UserWorkActionsMapper userWorkActionsMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void choice(DictChoiceParam param) {
        long userId = StpUtil.getLoginIdAsLong();
        BookDict bookDict = bookDictMapper.selectById(param.getBookId());
        Assert.notNull(bookDict, "为查询到当前词典信息！");
        List<UserBookDict> userBookDictList = this.baseMapper.selectAllByUserId(userId);
        if (CollectionUtils.isEmpty(userBookDictList)) {
            // 如果没有数据
            UserBookDict userBookDict = new UserBookDict();
            userBookDict.setUserId(userId);
            userBookDict.setBookId(bookDict.getBookId());
            userBookDict.setBookName(bookDict.getBookName());
            userBookDict.setStudied(0);
            userBookDict.setAction(SystemConstants.STATUS_OK);
            this.save(userBookDict);
            // TODO 异步生成学习汇总数据
            userActionsHandler(bookDict.getBookId());
        } else {
            // 有数据
            Optional<UserBookDict> bookDictOp = userBookDictList.stream()
                    .filter(userBookDict -> Objects.equals(userBookDict.getBookId(), bookDict.getBookId()))
                    .findFirst();
            // 先全部设置为不启用
            this.baseMapper.updateActionNoByUserId(userId);
            if (bookDictOp.isEmpty()) {
                // 如果为空，插新数据
                UserBookDict userBookDict = new UserBookDict();
                userBookDict.setUserId(userId);
                userBookDict.setBookId(bookDict.getBookId());
                userBookDict.setBookName(bookDict.getBookName());
                userBookDict.setStudied(0);
                userBookDict.setAction(SystemConstants.STATUS_OK);
                this.save(userBookDict);
                // TODO 异步生成学习汇总数据
                userActionsHandler(bookDict.getBookId());
            } else {
                // 更新
                UserBookDict dict = bookDictOp.get();
                dict.setAction(SystemConstants.STATUS_OK);
                this.updateById(dict);
            }
        }
    }

    @Override
    public List<BookDict> getAllDict() {
        return (List<BookDict>) Optional.ofNullable(redisTemplate.opsForValue().get(CacheConstants.ALL_BOOK_DICT))
                .orElseGet(() -> {
                    List<BookDict> dictList = bookDictMapper.selectList(new QueryWrapper<>());
                    redisTemplate.opsForValue().set(CacheConstants.ALL_BOOK_DICT, dictList, 24, TimeUnit.HOURS);
                    return dictList;
                });
    }

    @Override
    public UserBookDictVo getUserDict() {
        long userId = StpUtil.getLoginIdAsLong();
        return (UserBookDictVo) Optional.ofNullable(redisTemplate.opsForValue().get(CacheConstants.USER_BOOK_DICT + userId))
                .orElseGet(() -> {
                    UserBookDict userBookDict = this.baseMapper.selectActionByUserId(userId);
                    if (ObjectUtils.isEmpty(userBookDict)) {
                        return new UserBookDictVo();
                    }
                    UserBookDictVo vo = UserBookDictConverterMapper.INSTANCE.UserBookDictToVo(userBookDict);
                    BookDict bookDict = bookDictMapper.selectByBookId(userBookDict.getBookId());
                    vo.setBookSize(bookDict.getBookSize());
                    redisTemplate.opsForValue().set(CacheConstants.USER_BOOK_DICT + userId, vo, 24, TimeUnit.HOURS);
                    return vo;
                });
    }

    @Override
    public UserWrongWordVo getWrongBook() {
        long userId = StpUtil.getLoginIdAsLong();
        return (UserWrongWordVo) Optional.ofNullable(redisTemplate.opsForValue().get(CacheConstants.USER_WRONG_WORK + userId))
                .orElseGet(() -> {
                    UserBookDict userBookDict = this.baseMapper.selectActionByUserId(userId);
                    UserWrongWordVo vo = new UserWrongWordVo();
                    vo.setUserId(userId);
                    if (ObjectUtils.isEmpty(userBookDict)) {
                        return vo;
                    }
                    Integer count = userWrongWordMapper.selectCountByUserIdAndBookId(userId, userBookDict.getBookId());
                    vo.setBookId(userBookDict.getBookId());
                    vo.setCount(count);
                    redisTemplate.opsForValue().set(CacheConstants.USER_WRONG_WORK + userId, vo, 24, TimeUnit.HOURS);
                    return vo;
                });
    }

    @Override
    public TodayVo getToday() {
        long userId = StpUtil.getLoginIdAsLong();
        return (TodayVo) Optional.ofNullable(redisTemplate.opsForValue().get(CacheConstants.TODAY + userId))
                .orElseGet(() -> {
                    TodayVo vo = new TodayVo();
                    Integer count = userWorkActionsMapper.selectTodayCountByUserId(userId);
                    vo.setStudied(count);
                    redisTemplate.opsForValue().set(CacheConstants.TODAY + userId, vo, 24, TimeUnit.HOURS);
                    return vo;
                });
    }

    /**
     * 生成学习汇总数据
     * @param bookId 词典 id
     */
    private void userActionsHandler(String bookId) {
        List<Word> wordList = wordService.getWordListByBookId(bookId);
        if (!CollectionUtils.isEmpty(wordList)) {
            List<UserWorkActions> actionsList = new ArrayList<>();
            for (Word word: wordList) {
                UserWorkActions actions = new UserWorkActions();
                actions.setUserId(StpUtil.getLoginIdAsLong());
                actions.setBookId(bookId);
                actions.setWordId(word.getId());
                actionsList.add(actions);
            }
            userWorkActionsService.saveBatch(actionsList);
        }
    }

}
