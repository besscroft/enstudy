package dev.heming.enstudy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import dev.heming.enstudy.common.entity.FeedBack;
import dev.heming.enstudy.common.param.feedback.FeedBackParam;

import java.util.List;

/**
 * @Description 反馈 服务
 * @Author Bess Croft
 * @Date 2023/9/23 15:54
 */
public interface FeedBackService extends IService<FeedBack> {

    /**
     * 反馈分页
     * @param pageNum 页码
     * @param pageSize 页大小
     * @return 分页列表
     */
    List<FeedBack> feedbackPage(Integer pageNum, Integer pageSize);

    /**
     * 反馈提交
     * @param param 请求参数
     */
    void submit(FeedBackParam param);

}
