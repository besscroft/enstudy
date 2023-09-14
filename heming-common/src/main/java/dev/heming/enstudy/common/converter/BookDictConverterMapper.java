package dev.heming.enstudy.common.converter;

import dev.heming.enstudy.common.entity.BookDict;
import dev.heming.enstudy.common.param.dict.BookDictAddParam;
import dev.heming.enstudy.common.param.dict.BookDictUpdateParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description 词典转换器
 * @Author Bess Croft
 * @Date 2023/9/14 21:13
 */
@Mapper(componentModel = "spring")
public interface BookDictConverterMapper {

    BookDictConverterMapper INSTANCE = Mappers.getMapper(BookDictConverterMapper.class);

    BookDict AddParamToBookDict(BookDictAddParam param);

    BookDict UpdateParamToBookDict(BookDictUpdateParam param);

}
