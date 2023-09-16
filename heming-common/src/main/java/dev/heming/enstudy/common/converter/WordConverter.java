package dev.heming.enstudy.common.converter;

import dev.heming.enstudy.common.entity.Word;
import dev.heming.enstudy.common.param.word.WordAddParam;
import dev.heming.enstudy.common.param.word.WordUpdateParam;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * @Description 单词转换器
 * @Author Bess Croft
 * @Date 2023/9/14 21:56
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WordConverter {

    Word AddParamToWord(WordAddParam param);

    Word UpdateParamToWord(WordUpdateParam param);

}
