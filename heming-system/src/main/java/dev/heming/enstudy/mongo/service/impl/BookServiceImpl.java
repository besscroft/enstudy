package dev.heming.enstudy.mongo.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import dev.heming.enstudy.common.constant.SystemConstants;
import dev.heming.enstudy.common.entity.*;
import dev.heming.enstudy.common.param.book.ActionsParam;
import dev.heming.enstudy.common.vo.userBookDict.UserBookDictVo;
import dev.heming.enstudy.mapper.UserBookDictMapper;
import dev.heming.enstudy.mapper.UserWorkActionsMapper;
import dev.heming.enstudy.mapper.UserWrongWordMapper;
import dev.heming.enstudy.mapper.WordMapper;
import dev.heming.enstudy.mongo.service.BookService;
import dev.heming.enstudy.common.param.book.GetBookParam;
import dev.heming.enstudy.service.UserBookDictService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description 词库 Service 实现类
 * @Author Bess Croft
 * @Date 2023/8/26 15:26
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final MongoTemplate mongoTemplate;
    private final UserBookDictService userBookDictService;
    private final UserWorkActionsMapper userWorkActionsMapper;
    private final WordMapper wordMapper;
    private final UserBookDictMapper userBookDictMapper;
    private final UserWrongWordMapper userWrongWordMapper;

    @Override
    public Book getBook(GetBookParam param) {
        Query query = new Query(
                Criteria
                        .where("bookId").is(param.getBookId())
                        .and("wordRank").is(param.getWordRank())
                        .and("headWord").is(param.getHeadWord())
        );
        Book result = mongoTemplate.findOne(query, Book.class);
        return result;
    }

    @Override
    public Book getWord() {
        // TODO 优化
        long userId = StpUtil.getLoginIdAsLong();
        UserBookDictVo userDict = userBookDictService.getUserDict();
        Assert.notNull(userDict, "还未选择词典！");
        UserWorkActions actions = userWorkActionsMapper.selectByUserIdAndBookId(userId, userDict.getBookId());
        Assert.notNull(actions, "未生成学习数据，请联系管理员！");
        Word word = wordMapper.selectById(actions.getWordId());
        Assert.notNull(word, "单词数据不存在，请联系管理员！");
        Query query = new Query(
                Criteria
                        .where("bookId").is(userDict.getBookId())
                        .and("wordRank").is(word.getWordRank())
                        .and("headWord").is(word.getHeadWord())
        );
        Book result = mongoTemplate.findOne(query, Book.class);
        return result;
    }

    @Override
    public List<Book> getWordList() {
        // TODO 优化
        long userId = StpUtil.getLoginIdAsLong();
        UserBookDictVo userDict = userBookDictService.getUserDict();
        Assert.notNull(userDict, "还未选择词典！");
        List<UserWorkActions> actionsList = userWorkActionsMapper.selectListByUserIdAndBookId(userId, userDict.getBookId());
        Assert.notEmpty(actionsList, "未生成学习数据，请联系管理员！");
        List<Long> wordIdList = actionsList.stream().map(UserWorkActions::getWordId).collect(Collectors.toList());
        List<Word> wordList = wordMapper.selectList(new QueryWrapper<Word>().in("id", wordIdList));
        Assert.notEmpty(wordList, "单词数据不存在，请联系管理员！");
        List<String> bookIds = wordList.stream().map(Word::getBookId).collect(Collectors.toList());
        List<Integer> wordRanks = wordList.stream().map(Word::getWordRank).collect(Collectors.toList());
        List<String> headWords = wordList.stream().map(Word::getHeadWord).collect(Collectors.toList());
        Query query = new Query(
                Criteria
                        .where("bookId").in(bookIds)
                        .and("wordRank").in(wordRanks)
                        .and("headWord").in(headWords)
        );
        List<Book> bookList = mongoTemplate.find(query, Book.class);
        return bookList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void actions(ActionsParam param) {
        long userId = StpUtil.getLoginIdAsLong();
        Word word = wordMapper.selectByWordJsonId(param.getWordJsonId());
        UserWorkActions workActions = userWorkActionsMapper.selectByUserIdAndBookIdAndWordId(userId, param.getBookId(), word.getId());
        UserBookDict userBookDict = userBookDictMapper.selectActionByUserId(userId);
        if (Objects.equals(workActions.getState(), SystemConstants.STATUS_NO)) {
            userBookDict.setStudied(userBookDict.getStudied() + 1);
            workActions.setState(SystemConstants.STATUS_OK);
        }
        if (Objects.equals(param.getState(), 0)) {
            UserWrongWord wrongWord = userWrongWordMapper.selectByUserIdAndBookIdAndWordId(userId, param.getBookId(), word.getId());
            if (Objects.isNull(wrongWord)) {
                UserWrongWord userWrongWord = new UserWrongWord();
                userWrongWord.setUserId(userId);
                userWrongWord.setBookId(param.getBookId());
                userWrongWord.setWordId(word.getId());
                userWrongWord.setFailCount(0);
                userWrongWordMapper.insert(userWrongWord);
            } else {
                wrongWord.setFailCount(wrongWord.getFailCount() + 1);
                userWrongWordMapper.updateById(wrongWord);
            }
        }
        userWorkActionsMapper.updateById(workActions);
        userBookDictMapper.updateById(userBookDict);
    }

}
