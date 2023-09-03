package dev.heming.enstudy.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @Description 用户学习行为
 * @Author Bess Croft
 * @Date 2023/8/31 18:15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "enstudy_user_work_actions")
@Schema(title = "用户学习行为")
public class UserWorkActions extends BaseEntity {

    @TableId(type = IdType.AUTO)
    @Schema(title = "id", type = "Long")
    private Long id;

    /** 单词 id */
    @TableField(value = "word_id")
    @Schema(title = "单词 id", type = "Long")
    private Long wordId;

    /** 用户 id */
    @TableField(value = "user_id")
    @Schema(title = "用户 id", type = "Long")
    private Long userId;

    /** 词典 id */
    @TableField(value = "book_id")
    @Schema(title = "词典 id", type = "String")
    private String bookId;

    /** 学习状态：0->未学；1->已学 */
    @TableField(value = "state")
    @Schema(title = "学习状态：0->未学；1->已学", type = "Long")
    private Long state;

}
