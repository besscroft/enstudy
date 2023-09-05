package dev.heming.enstudy.common.vo.userWrongWord;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Description 当前用户的错题本返回参数
 * @Author Bess Croft
 * @Date 2023/9/5 13:41
 */
@Data
@Schema(title = "当前用户的错题本返回参数")
public class UserWrongWordVo {

    @Schema(title = "用户 id", type = "Long")
    private Long userId;

    @Schema(title = "词典 id", type = "String")
    private String bookId;

    @Schema(title = "错题数量", type = "Integer")
    private Integer count;

}
