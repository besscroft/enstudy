package dev.heming.enstudy.common.param.book;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Description 词典参数
 * @Author Bess Croft
 * @Date 2023/8/26 15:27
 */
@Data
@Schema(title = "词典参数")
public class GetBookParam {

    /** 词典 ID */
    @NotNull(message = "词典 ID 未填！")
    @Schema(title = "词典 ID", type = "Long", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long bookId;

    /** 单词序号 */
    @NotNull(message = "单词序号未填！")
    @Schema(title = "单词序号", type = "Long", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long wordRank;

    /** 单词 */
    @NotBlank(message = "单词未填！")
    @Schema(title = "单词", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    private String headWord;

}
