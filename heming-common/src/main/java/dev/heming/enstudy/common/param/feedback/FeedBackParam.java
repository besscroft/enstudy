package dev.heming.enstudy.common.param.feedback;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Description 反馈接口请求参数
 * @Author Bess Croft
 * @Date 2023/9/23 16:10
 */
@Data
@Schema(title = "反馈接口请求参数")
public class FeedBackParam {

    /** 单词数据 id */
    @NotBlank(message = "单词数据 id 未填！")
    @Schema(title = "单词数据 id", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    private String wordJsonId;

    /** 词典 ID */
    @NotBlank(message = "词典 ID 未填！")
    @Schema(title = "词典 ID", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    private String bookId;

    /** 单词序号 */
    @NotNull(message = "单词序号未填！")
    @Schema(title = "单词序号", type = "Long", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long wordRank;

    /** 单词 */
    @NotBlank(message = "单词未填！")
    @Schema(title = "单词", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    private String headWord;

    /** 反馈内容 */
    @NotBlank(message = "反馈内容未填！")
    @Schema(title = "反馈内容", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;

}
