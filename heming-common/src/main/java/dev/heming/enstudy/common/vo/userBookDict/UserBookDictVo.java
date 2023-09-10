package dev.heming.enstudy.common.vo.userBookDict;

import dev.heming.enstudy.common.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description 当前用户的活动词典返回参数
 * @Author Bess Croft
 * @Date 2023/9/5 13:24
 */
@Data
@Schema(title = "当前用户的活动词典返回参数")
@EqualsAndHashCode(callSuper = true)
public class UserBookDictVo extends BaseEntity {

    @Schema(title = "id", type = "Long")
    private Long id;

    @Schema(title = "用户 id", type = "Long")
    private Long userId;

    @Schema(title = "词典 id", type = "String")
    private String bookId;

    @Schema(title = "词典名称", type = "Long")
    private String bookName;

    @Schema(title = "已学词数", type = "Long")
    private Integer studied;

    @Schema(title = "用户使用状态：0->停用状态；1->使用状态", type = "Long")
    private Integer action;

    @Schema(title = "词典词数", type = "Integer")
    private Integer bookSize;

}
