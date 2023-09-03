package dev.heming.enstudy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import dev.heming.enstudy.common.entity.UserBookDict;
import dev.heming.enstudy.common.param.dict.DictChoiceParam;

/**
 * @Description 用户活动词库 服务
 * @Author Bess Croft
 * @Date 2023/8/31 21:26
 */
public interface UserBookDictService extends IService<UserBookDict> {

    /**
     * 用户选择词典
     * @param param 参数
     */
    void choice(DictChoiceParam param);

}
