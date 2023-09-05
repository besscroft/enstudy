package dev.heming.enstudy.common.param.book;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Description 单词反馈接口请求参数
 * @Author Bess Croft
 * @Date 2023/9/5 16:53
 */
@Data
@Schema(title = "单词反馈接口请求参数")
public class ActionsParam {

    /** 单词数据 id */
    @NotBlank(message = "单词数据 id 未填！")
    @Schema(title = "单词数据 id", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    private String wordJsonId;

    /** 词库 ID */
    @NotBlank(message = "词库 ID 未填！")
    @Schema(title = "词库 ID", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    private String bookId;

    /** 单词序号 */
    @NotNull(message = "单词序号未填！")
    @Schema(title = "单词序号", type = "Long", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long wordRank;

    /** 单词 */
    @NotBlank(message = "单词未填！")
    @Schema(title = "单词", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    private String headWord;

    /** 状态: 0-不认识，1-学会了 */
    @NotNull(message = "状态未填！")
    @Schema(title = "状态: 0-不认识，1-学会了", type = "Integer", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer state;

}
