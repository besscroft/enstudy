package dev.heming.enstudy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import dev.heming.enstudy.common.entity.BookDict;
import dev.heming.enstudy.common.entity.UserBookDict;
import dev.heming.enstudy.common.param.dict.DictChoiceParam;
import dev.heming.enstudy.common.vo.statistics.TodayVo;
import dev.heming.enstudy.common.vo.userBookDict.UserBookDictVo;
import dev.heming.enstudy.common.vo.userWrongWord.UserWrongWordVo;

import java.util.List;

/**
 * @Description 用户活动词典 服务
 * @Author Bess Croft
 * @Date 2023/8/31 21:26
 */
public interface UserBookDictService extends IService<UserBookDict> {

    /**
     * 用户选择词典
     * @param param 参数
     */
    void choice(DictChoiceParam param);

    /**
     * 获取所有词典信息
     * @return 词典列表
     */
    List<BookDict> getAllDict();

    /**
     * 获取当前用户的活动词典
     * @return 活动词典
     */
    UserBookDictVo getUserDict();

    /**
     * 获取当前用户错题本
     * @return 用户错题本
     */
    UserWrongWordVo getWrongBook();

    /**
     * 获取今日记录
     * @return 今日记录
     */
    TodayVo getToday();

}
