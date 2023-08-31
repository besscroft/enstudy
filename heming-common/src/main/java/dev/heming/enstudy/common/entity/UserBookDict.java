package dev.heming.enstudy.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @Description 用户活动词库
 * @Author Bess Croft
 * @Date 2023/8/31 18:15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "enstudy_user_book_dict")
@Schema(title = "用户活动词库")
public class UserBookDict extends BaseEntity {

    @TableId(type = IdType.AUTO)
    @Schema(title = "id", type = "Long")
    private Long id;

    /** 用户 id */
    @TableField(value = "user_id")
    @Schema(title = "用户 id", type = "Long")
    private Long userId;

    /** 词典 id */
    @TableField(value = "book_id")
    @Schema(title = "词典 id", type = "String")
    private String bookId;

    /** 词典名称 */
    @TableField(value = "book_name")
    @Schema(title = "词典名称", type = "Long")
    private String bookName;

    /** 已学词数 */
    @TableField(value = "studied")
    @Schema(title = "已学词数", type = "Long")
    private Integer studied;

    /** 用户使用状态：0->停用状态；1->使用状态 */
    @TableField(value = "action")
    @Schema(title = "用户使用状态：0->停用状态；1->使用状态", type = "Long")
    private Integer action;

}
