package com.song.service;

import java.util.concurrent.TimeUnit;

public interface RedisService<T> {
    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    boolean existsKey(String key);

    /**
     * 删除key
     *
     * @param key
     */
    void deleteKey(String key);

    /**
     * 设置key过期时间
     *
     * @param key      key
     * @param time     过期时间
     * @param timeUnit 时间单位
     */
    void expireKey(String key, long time, TimeUnit timeUnit);

    /**
     * 获取过期时间
     *
     * @param key
     * @param timeUnit 时间单位
     * @return
     */
    long getKeyExpire(String key, TimeUnit timeUnit);

    /**
     * 设置key值
     *
     * @param key
     * @param value
     */
    void setKey(String key, T value);

    /**
     * 获取值
     *
     * @param key
     * @return
     */
    T getValue(String key);
}
