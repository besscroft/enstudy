package dev.heming.enstudy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.heming.enstudy.common.entity.UserWorkActions;
import org.apache.ibatis.annotations.Param;

/**
 * @Description 用户学习行为 Mapper 接口
 * @Author Bess Croft
 * @Date 2023/8/31 20:56
 */
public interface UserWorkActionsMapper extends BaseMapper<UserWorkActions> {

    /**
     * 获取用户今日所学单词数量（无需区分词典）
     * @param userId 用户 id
     * @return 今日所学单词数量
     */
    Integer selectTodayCountByUserId(@Param("userId") Long userId);

}
