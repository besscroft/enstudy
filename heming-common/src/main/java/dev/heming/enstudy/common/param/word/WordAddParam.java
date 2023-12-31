package dev.heming.enstudy.common.param.word;

import dev.heming.enstudy.common.entity.Book;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Description 新增单词请求参数
 * @Author Bess Croft
 * @Date 2023/9/14 21:34
 */
@Data
@Schema(title = "新增单词请求参数")
public class WordAddParam {

    /** 单词数据 */
    @Schema(title = "单词数据", type = "dev.heming.enstudy.common.entity.Book", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "单词数据不能为空！")
    private Book wordJson;

    /** 单词序号 */
    @Schema(title = "单词序号", type = "Integer", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "单词序号不能为空！")
    private Integer wordRank;

    /** 单词 */
    @Schema(title = "单词", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "单词不能为空！")
    private String headWord;

    /** 词典 id */
    @Schema(title = "词典 id", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "词典 id 不能为空！")
    private String bookId;

}
