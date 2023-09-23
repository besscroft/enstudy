package dev.heming.enstudy.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @Description 反馈表
 * @Author Bess Croft
 * @Date 2023/9/23 15:51
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "enstudy_feedback")
@Schema(title = "反馈表")
public class FeedBack extends BaseEntity {

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

    /** 反馈内容 */
    @TableField(value = "content")
    @Schema(title = "反馈内容", type = "String")
    private String content;

}
