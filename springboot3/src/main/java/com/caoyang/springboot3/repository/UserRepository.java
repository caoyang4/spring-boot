package com.caoyang.springboot3.repository;

import com.caoyang.springboot3.dao.UserDO;

import java.util.List;

/**
 * @author caoyang
 * @create 2023-12-14 13:18
 */
public interface UserRepository {

    UserDO getUser(String name);

    List<UserDO> getUserList();

    Boolean insert(UserDO userDO);

}
