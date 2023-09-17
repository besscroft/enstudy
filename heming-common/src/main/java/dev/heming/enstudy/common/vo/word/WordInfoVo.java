package dev.heming.enstudy.common.vo.word;

import dev.heming.enstudy.common.entity.Book;
import dev.heming.enstudy.common.entity.Word;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description 单词详情返回参数
 * @Author Bess Croft
 * @Date 2023/9/17 19:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "单词详情返回参数")
public class WordInfoVo extends Word {

    @Schema(title = "单词数据", type = "dev.heming.enstudy.common.entity.Book")
    private Book wordJson;

}
