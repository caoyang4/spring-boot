package com.caoyang.springboot3.repository.impl;

import com.caoyang.springboot3.dao.UserDO;
import com.caoyang.springboot3.repository.RedisClient;
import com.caoyang.springboot3.repository.UserCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author caoyang
 * @create 2023-12-14 13:41
 */
@Slf4j
@Repository
public class UserCacheImpl  implements UserCache {

    @Autowired
    private RedisClient redisClient;

    private String formatKey(String name) {
        return "user:" + name;
    }

    @Override
    public UserDO getUser(String name) {
        log.info("Getting user from cache: {}", formatKey(name));
        return redisClient.get(formatKey(name), UserDO.class);
    }

    @Override
    public void cacheUser(UserDO userDO) {
        log.info("Caching user: {}", formatKey(userDO.getLoginName()));
        redisClient.set(formatKey(userDO.getLoginName()), userDO, 300);
    }

}
