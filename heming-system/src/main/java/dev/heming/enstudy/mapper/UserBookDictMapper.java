package dev.heming.enstudy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.heming.enstudy.common.entity.UserBookDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 用户活动词典 Mapper 接口
 * @Author Bess Croft
 * @Date 2023/8/31 20:55
 */
public interface UserBookDictMapper extends BaseMapper<UserBookDict> {

    /**
     * 查询用户所有活动词典
     * @param userId 用户 id
     * @return 活动词典集合
     */
    List<UserBookDict> selectAllByUserId(@Param("userId") Long userId);

    /**
     * 设置用户所有数据为停用状态
     * @param userId 用户 id
     * @return 数量
     */
    int updateActionNoByUserId(@Param("userId") Long userId);

    /**
     * 获取用户的活动词典
     * @param userId 用户 id
     * @return 活动词典
     */
    UserBookDict selectActionByUserId(@Param("userId") Long userId);

}
