package dev.heming.enstudy.common.vo.statistics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Description 今日统计返回参数
 * @Author Bess Croft
 * @Date 2023/9/5 14:06
 */
@Data
@Schema(title = "今日统计返回参数")
public class TodayVo {

    @Schema(title = "今日已学单词数", type = "Integer")
    private Integer studied;

}
