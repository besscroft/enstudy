package dev.heming.enstudy.common.converter;

import dev.heming.enstudy.common.entity.UserBookDict;
import dev.heming.enstudy.common.vo.userBookDict.UserBookDictVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * @Description 用户活动词典转换器
 * @Author Bess Croft
 * @Date 2023/9/5 13:29
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserBookDictConverter {

    UserBookDictVo UserBookDictToVo(UserBookDict userBookDict);

}
