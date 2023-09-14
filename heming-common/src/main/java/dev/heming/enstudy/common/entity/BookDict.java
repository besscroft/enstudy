package dev.heming.enstudy.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @Description 词典
 * @Author Bess Croft
 * @Date 2023/8/26 17:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "enstudy_book_dict")
@Schema(title = "词典")
public class BookDict extends BaseEntity {

    @TableId(type = IdType.AUTO)
    @Schema(title = "id", type = "Long")
    private Long id;

    /** 词典 id */
    @TableField(value = "book_id")
    @Schema(title = "词典 id", type = "String")
    private String bookId;

    /** 词典名称 */
    @TableField(value = "book_name")
    @Schema(title = "词典名称", type = "String")
    private String bookName;

    /** 词典词数 */
    @TableField(value = "book_size")
    @Schema(title = "词典词数", type = "Integer")
    private Integer bookSize;

}
