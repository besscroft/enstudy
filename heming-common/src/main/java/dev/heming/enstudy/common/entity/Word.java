package dev.heming.enstudy.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @Description 单词
 * @Author Bess Croft
 * @Date 2023/8/31 18:11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "enstudy_word")
@Schema(title = "单词")
public class Word extends BaseEntity {

    @TableId(type = IdType.AUTO)
    @Schema(title = "id", type = "Long")
    private Long id;

    /** 单词数据 id */
    @TableField(value = "word_json_id")
    @Schema(title = "单词数据 id", type = "String")
    private String wordJsonId;

    /** 单词序号 */
    @TableField(value = "word_rank")
    @Schema(title = "单词序号", type = "Integer")
    private Integer wordRank;

    /** 单词 */
    @TableField(value = "head_word")
    @Schema(title = "单词", type = "String")
    private String headWord;

    /** 词典 id */
    @TableField(value = "book_id")
    @Schema(title = "词典 id", type = "String")
    private String bookId;

}
