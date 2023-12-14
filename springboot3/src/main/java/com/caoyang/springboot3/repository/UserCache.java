package com.caoyang.springboot3.repository;

import com.caoyang.springboot3.dao.UserDO;

/**
 * @author caoyang
 * @create 2023-12-14 13:21
 */
public interface UserCache {

    UserDO getUser(String name);

    void cacheUser(UserDO userDO);

}
