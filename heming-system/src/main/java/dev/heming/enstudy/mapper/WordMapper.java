package dev.heming.enstudy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.heming.enstudy.common.entity.Word;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 单词 Mapper 接口
 * @Author Bess Croft
 * @Date 2023/8/31 21:33
 */
public interface WordMapper extends BaseMapper<Word> {

    /**
     * 查询词库的所有单词
     * @param bookId 词库 id
     * @return 单词列表
     */
    List<Word> selectAllByBookId(@Param("bookId") String bookId);

}
