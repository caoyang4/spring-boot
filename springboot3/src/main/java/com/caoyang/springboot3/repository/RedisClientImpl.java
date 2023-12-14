package com.caoyang.springboot3.repository;

import com.caoyang.springboot3.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Objects;

/**
 * @author caoyang
 * @create 2023-12-14 13:25
 */
@Repository
public class RedisClientImpl implements RedisClient{

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public <T> T get(String key, Class<T> clz) {
        Object obj = redisTemplate.opsForValue().get(key);
        if (Objects.isNull(obj)) {
            return null;
        }
        return JsonUtils.json2Bean(JsonUtils.bean2Json(obj), clz);
    }

    @Override
    public <T> void set(String key, T obj, int expireTime) {
        redisTemplate.opsForValue().set(key, obj, Duration.ofSeconds(expireTime));
    }

}
