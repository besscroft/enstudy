package dev.heming.enstudy.common.param.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Description 用户状态更新请求参数
 * @Author Bess Croft
 * @Date 2023/8/26 17:10
 */
@Data
@Schema(title = "用户状态更新请求参数")
public class UserUpdateStatusParam {

    @Schema(title = "用户 id", type = "Long")
    @NotNull(message = "用户 id 不能为空")
    private Long userId;

    @Schema(title = "帐号启用状态：0->禁用；1->启用", type = "Integer")
    @NotNull(message = "帐号启用状态不能为空")
    private Integer status;

}
