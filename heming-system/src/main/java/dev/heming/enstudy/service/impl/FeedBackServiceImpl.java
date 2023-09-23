package dev.heming.enstudy.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import dev.heming.enstudy.common.entity.FeedBack;
import dev.heming.enstudy.common.entity.Word;
import dev.heming.enstudy.common.param.feedback.FeedBackParam;
import dev.heming.enstudy.mapper.FeedBackMapper;
import dev.heming.enstudy.mapper.WordMapper;
import dev.heming.enstudy.service.FeedBackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @Description 反馈 Service 实现类
 * @Author Bess Croft
 * @Date 2023/9/23 15:55
 */
@Service
@RequiredArgsConstructor
public class FeedBackServiceImpl extends ServiceImpl<FeedBackMapper, FeedBack> implements FeedBackService {

    private final WordMapper wordMapper;

    @Override
    public List<FeedBack> feedbackPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return this.list();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submit(FeedBackParam param) {
        FeedBack feedBack = new FeedBack();
        feedBack.setBookId(param.getBookId());
        feedBack.setContent(param.getContent());
        Word word = wordMapper.selectByWordJsonId(param.getWordJsonId());
        Assert.notNull(word, "单词不存在！");
        feedBack.setWordId(word.getId());
        long userId = StpUtil.getLoginIdAsLong();
        feedBack.setUserId(userId);
        this.baseMapper.insert(feedBack);
    }

}
