package dev.heming.enstudy.common.converter;

import dev.heming.enstudy.common.entity.UserBookDict;
import dev.heming.enstudy.common.vo.userBookDict.UserBookDictVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description 用户活动词库转换器
 * @Author Bess Croft
 * @Date 2023/9/5 13:29
 */
@Mapper(componentModel = "spring")
public interface UserBookDictConverterMapper {

    UserBookDictConverterMapper INSTANCE = Mappers.getMapper(UserBookDictConverterMapper.class);

    UserBookDictVo UserBookDictToVo(UserBookDict userBookDict);

}
