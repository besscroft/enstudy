package dev.heming.enstudy.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.heming.enstudy.common.constant.SystemConstants;
import dev.heming.enstudy.common.entity.BookDict;
import dev.heming.enstudy.common.entity.UserBookDict;
import dev.heming.enstudy.common.entity.UserWorkActions;
import dev.heming.enstudy.common.entity.Word;
import dev.heming.enstudy.common.param.dict.DictChoiceParam;
import dev.heming.enstudy.mapper.BookDictMapper;
import dev.heming.enstudy.mapper.UserBookDictMapper;
import dev.heming.enstudy.service.UserBookDictService;
import dev.heming.enstudy.service.UserWorkActionsService;
import dev.heming.enstudy.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @Description 用户活动词库 服务实现类
 * @Author Bess Croft
 * @Date 2023/8/31 21:37
 */
@Service
@RequiredArgsConstructor
public class UserBookDictServiceImpl extends ServiceImpl<UserBookDictMapper, UserBookDict> implements UserBookDictService {

    private final BookDictMapper bookDictMapper;
    private final WordService wordService;
    private final UserWorkActionsService userWorkActionsService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void choice(DictChoiceParam param) {
        long userId = StpUtil.getLoginIdAsLong();
        BookDict bookDict = bookDictMapper.selectById(param.getBookId());
        Assert.notNull(bookDict, "为查询到当前词库信息！");
        List<UserBookDict> userBookDictList = this.baseMapper.selectAllByUserId(userId);
        if (CollectionUtils.isEmpty(userBookDictList)) {
            // 如果没有数据
            UserBookDict userBookDict = new UserBookDict();
            userBookDict.setUserId(userId);
            userBookDict.setBookId(bookDict.getBookId());
            userBookDict.setBookName(bookDict.getBookName());
            userBookDict.setStudied(0);
            this.save(userBookDict);
            // TODO 异步生成学习汇总数据
            userActionsHandler(bookDict.getBookId());
        } else {
            // 有数据
            Optional<UserBookDict> bookDictOp = userBookDictList.stream()
                    .filter(userBookDict -> Objects.equals(userBookDict.getBookId(), param.getBookId()))
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

    /**
     * 生成学习汇总数据
     * @param bookId 词库 id
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
