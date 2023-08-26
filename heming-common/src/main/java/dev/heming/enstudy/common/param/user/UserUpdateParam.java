package dev.heming.enstudy.common.param.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Description 更新用户请求参数
 * @Author Bess Croft
 * @Date 2023/8/26 17:10
 */
@Data
@Schema(title = "更新用户请求参数")
public class UserUpdateParam {

    @Schema(title = "用户 id", type = "Long", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "用户 id 不能为空！")
    private Long id;

    @Schema(title = "头像(地址)", type = "String")
    private String avatar;

    @Schema(title = "角色", type = "String")
    private String role;

    @Schema(title = "邮箱", type = "String")
    private String email;

    @Schema(title = "昵称", type = "String")
    private String name;

    @Schema(title = "手机", type = "String")
    private String telephone;

    @Schema(title = "备注", type = "String")
    private String remark;

}