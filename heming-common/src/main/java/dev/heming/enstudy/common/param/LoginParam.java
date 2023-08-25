package dev.heming.enstudy.common.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Description 登录请求参数
 * @Author Bess Croft
 * @Date 2023/8/25 12:26
 */
@Data
@Schema(title = "登录请求参数")
public class LoginParam {

    /** 用户名 */
    @NotBlank(message = "用户名未填！")
    @Schema(title = "用户名", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    /** 密码 */
    @NotBlank(message = "密码未填！")
    @Schema(title = "密码", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

}
