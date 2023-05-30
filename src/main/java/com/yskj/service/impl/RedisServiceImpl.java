package com.yskj.service.impl;

import com.yskj.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl<T> implements RedisService<T> {
    @Resource
    private RedisTemplate<String, T> redisTemplate;

    @Override
    public boolean existsKey(String key) {
        if (StringUtils.isBlank(key)) {
            return false;
        } else {
            return redisTemplate.hasKey(key);
        }
    }

    @Override
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void expireKey(String key, long time, TimeUnit timeUnit) {
        redisTemplate.expire(key, time, timeUnit);
    }

    @Override
    public long getKeyExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }

    @Override
    public void setKey(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public T getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
