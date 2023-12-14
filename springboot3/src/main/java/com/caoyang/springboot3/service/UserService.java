package com.caoyang.springboot3.service;

import com.caoyang.springboot3.dao.UserDO;

import java.util.List;

/**
 * @author caoyang
 * @create 2023-12-14 10:04
 */
public interface UserService {

    UserDO getUser(String name);

    List<UserDO> getUserList();

    Boolean addUser(UserDO user);

}
