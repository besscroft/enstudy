package dev.heming.enstudy.common.converter;

import dev.heming.enstudy.common.entity.User;
import dev.heming.enstudy.common.param.user.UserAddParam;
import dev.heming.enstudy.common.param.user.UserUpdateParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description 用户对象转换器
 * @Author Bess Croft
 * @Date 2023/8/26 17:13
 */
@Mapper(componentModel = "spring")
public interface UserConverterMapper {

    UserConverterMapper INSTANCE = Mappers.getMapper(UserConverterMapper.class);

    User AddParamToUser(UserAddParam param);

    User UpdateParamToUser(UserUpdateParam param);

}
