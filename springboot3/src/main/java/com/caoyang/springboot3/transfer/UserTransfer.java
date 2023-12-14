package com.caoyang.springboot3.transfer;

import com.caoyang.springboot3.dao.UserDO;
import com.caoyang.springboot3.vo.UserVO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author caoyang
 * @create 2023-12-14 10:12
 */
@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.WARN
)
public interface UserTransfer {

    UserTransfer INSTANCE = Mappers.getMapper(UserTransfer.class);

    @Named("toVOList")
    List<UserVO> toVOList(List<UserDO> userDOList);

    @Named("toVO")
    UserVO toVO(UserDO userDO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Named("toDO")
    UserDO toDO(UserVO userVO);

}
