package dev.heming.enstudy.common.param.dict;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Description 用户选择词典接口 请求参数
 * @Author Bess Croft
 * @Date 2023/9/3 20:15
 */
@Data
@Schema(title = "用户选择词典接口请求参数")
public class DictChoiceParam {

    @Schema(title = "词典 id", type = "Long", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "词典 id不能为空！")
    private Long bookId;

}
