package com.caoyang.springboot3.mapper;

import com.caoyang.springboot3.dao.UserDO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author caoyang
 * @create 2023-12-14 11:15
 */
@Component
public interface UserMapper extends Mapper<UserDO> {

}
