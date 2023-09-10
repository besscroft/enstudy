package dev.heming.enstudy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.heming.enstudy.common.entity.BookDict;
import org.apache.ibatis.annotations.Param;

/**
 * @Description 词典 Mapper 接口
 * @Author Bess Croft
 * @Date 2023/8/26 17:04
 */
public interface BookDictMapper extends BaseMapper<BookDict> {

    /**
     * 查询词典信息
     * @param bookId 词典 id
     * @return 词典信息
     */
    BookDict selectByBookId(@Param("bookId") String bookId);

    /**
     * 查询词典总数
     * @return 词典总数
     */
    Integer selectDictCount();

}
