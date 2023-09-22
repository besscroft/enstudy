package dev.heming.enstudy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.heming.enstudy.common.entity.UserWorkActions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 获取一个用户还未学习的单词
     * @param userId 用户 id
     * @param bookId 词典 id
     * @return 单词
     */
    UserWorkActions selectByUserIdAndBookId(@Param("userId") Long userId,
                                            @Param("bookId") String bookId);

    /**
     * 获取10个用户还未学习的单词
     * @param userId 用户 id
     * @param bookId 词典 id
     * @return
     */
    List<UserWorkActions> selectListByUserIdAndBookId(@Param("userId") Long userId,
                                                      @Param("bookId") String bookId);

    /**
     * 查询用户学习行为信息
     * @param userId 用户 id
     * @param bookId 词典 id
     * @param wordId 单词 id
     * @return 学习行为信息
     */
    UserWorkActions selectByUserIdAndBookIdAndWordId(@Param("userId") Long userId,
                                                     @Param("bookId") String bookId,
                                                     @Param("wordId") Long wordId);

    /**
     * 查询用户当前词典是否有学习数据
     * @param userId 用户 id
     * @param bookId 词典 id
     * @return 学习数据是否存在 null->不存在 1->存在
     */
    Integer selectActionsDataExistByUserIdAndBookId(@Param("userId") Long userId,
                                                    @Param("bookId") String bookId);

}
