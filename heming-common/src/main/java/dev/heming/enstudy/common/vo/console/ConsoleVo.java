package dev.heming.enstudy.common.vo.console;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2023/9/5 14:33
 */
@Data
@Schema(title = "控制台统计返回参数")
public class ConsoleVo {

    @Schema(title = "单词总数", type = "Integer")
    private Integer wordCount;

    @Schema(title = "词库", type = "Integer")
    private Integer dictCount;

    @Schema(title = "用户", type = "Integer")
    private Integer userCount;

}
