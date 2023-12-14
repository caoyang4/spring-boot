package com.caoyang.springboot3.repository;

/**
 * @author caoyang
 * @create 2023-12-14 13:22
 */
public interface RedisClient {

    <T> T get(String key, Class<T> clz);

    <T> void set(String key, T obj, int expireTime);

}
