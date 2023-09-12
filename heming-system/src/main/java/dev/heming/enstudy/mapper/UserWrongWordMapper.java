package dev.heming.enstudy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.heming.enstudy.common.entity.UserWrongWord;
import org.apache.ibatis.annotations.Param;

/**
 * @Description 用户错题本 Mapper 接口
 * @Author Bess Croft
 * @Date 2023/8/31 21:25
 */
public interface UserWrongWordMapper extends BaseMapper<UserWrongWord> {

    /**
     * 查询用户错题数量
     * @param userId 用户 id
     * @param bookId 词典 id
     * @return 用户错题数量
     */
    Integer selectCountByUserIdAndBookId(@Param("userId") Long userId,
                                         @Param("bookId") String bookId);

    /**
     * 查询错题本信息
     * @param userId 用户 id
     * @param bookId 词典 id
     * @param wordId 单词 id
     * @return 用户错题本信息
     */
    UserWrongWord selectByUserIdAndBookIdAndWordId(@Param("userId") Long userId,
                                                   @Param("bookId") String bookId,
                                                   @Param("wordId") Long wordId);

    /**
     * 查询用户错题本单个单词 id
     * @param userId 用户 id
     * @param bookId 词典 id
     * @return 单词 id
     */
    Long selectWordIdByUserIdAndBookId(@Param("userId") Long userId,
                                       @Param("bookId") String bookId);

}
