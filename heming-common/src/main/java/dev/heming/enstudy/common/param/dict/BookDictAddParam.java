package dev.heming.enstudy.common.param.dict;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Description 新增词典请求参数
 * @Author Bess Croft
 * @Date 2023/9/14 21:03
 */
@Data
@Schema(title = "新增词典请求参数")
public class BookDictAddParam {

    /** 词典 id */
    @Schema(title = "词典 id", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "词典 id 不能为空！")
    private String bookId;

    /** 词典名称 */
    @Schema(title = "词典名称", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "词典名称不能为空！")
    private String bookName;

    /** 词典词数 */
    @Schema(title = "词典词数", type = "Integer", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "词典词数不能为空！")
    private Integer bookSize;

}
