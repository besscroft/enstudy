package dev.heming.enstudy.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @Description 词典实体
 * @Author Bess Croft
 * @Date 2023/8/26 17:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "book_dict")
@Schema(title = "词典实体")
public class BookDict extends BaseEntity {

    @TableId(type = IdType.AUTO)
    @Schema(title = "用户 id", type = "Long")
    private Long id;

    /** 词典id */
    @TableField(value = "book_id", select = false)
    @Schema(title = "词典id", type = "String")
    private String bookId;

    /** 词典名称 */
    @TableField(value = "book_name", select = false)
    @Schema(title = "词典名称", type = "String")
    private String bookName;

    /** 词典词数 */
    @TableField(value = "book_size")
    @Schema(title = "词典词数", type = "Integer")
    private Integer bookSize;

}
