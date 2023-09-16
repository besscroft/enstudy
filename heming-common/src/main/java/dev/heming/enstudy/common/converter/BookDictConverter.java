package dev.heming.enstudy.common.converter;

import dev.heming.enstudy.common.entity.BookDict;
import dev.heming.enstudy.common.param.dict.BookDictAddParam;
import dev.heming.enstudy.common.param.dict.BookDictUpdateParam;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * @Description 词典转换器
 * @Author Bess Croft
 * @Date 2023/9/14 21:13
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookDictConverter {

    BookDict AddParamToBookDict(BookDictAddParam param);

    BookDict UpdateParamToBookDict(BookDictUpdateParam param);

}
