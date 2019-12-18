package com.song.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

/**
 * @author XiaoSong
 * @date 2019-12-18 13:52
 * 日期处理工具
 * 提供date转string,默认日期格式：yyyy-MM-dd mm:hh:ss
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    /**
     * 时间格式化缓存
     */
    private final static HashMap<String, DateTimeFormatter> FORMATTER_CACHE = new HashMap(16);
    /**
     * 时间格式化缓存大小
     */
    private final static int PATTERN_CACHE_SIZE = 16;
    /**
     * 默认转化格式
     */
    private final static String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式化时间，Date转换为String
     *
     * @param date    date
     * @param pattern 格式
     * @return
     */
    public static String format(Date date, String pattern) {
        return format(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()), pattern);
    }

    /**
     * 格式化时间，Date转换为String
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()), DEFAULT_PATTERN);
    }

    /**
     * 获取当前日期+时间字符串（"yyyy-MM-dd hh:mm:ss"）
     *
     * @return
     */
    public static String getNowDatetime() {
        return format(LocalDateTime.now(ZoneId.systemDefault()), DEFAULT_PATTERN);
    }

    /**
     * localDateTime转换为格式化时间
     *
     * @param localDateTime localDateTime
     * @param pattern       格式
     * @return
     */
    public static String format(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = createCacheFormatter(pattern);
        return localDateTime.format(formatter);
    }

    /**
     * 在缓存中创建DateTimeFormatter
     *
     * @param pattern 格式
     * @return
     */
    private static DateTimeFormatter createCacheFormatter(String pattern) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("Invalid pattern specification");
        }
        DateTimeFormatter formatter = FORMATTER_CACHE.get(pattern);
        if (formatter == null) {
            if (FORMATTER_CACHE.size() < PATTERN_CACHE_SIZE) {
//                创建DatetimeFormatter 并缓存到FORMATTER_CACHE中
                formatter = DateTimeFormatter.ofPattern(pattern);
                DateTimeFormatter oldFormatter = FORMATTER_CACHE.putIfAbsent(pattern, formatter);
                if (oldFormatter != null) {
                    formatter = oldFormatter;
                }
            } else {
//                FORMATTER_CACHE缓存已满
                formatter = DateTimeFormatter.ofPattern(pattern);
//                自动清除缓存
                FORMATTER_CACHE.clear();
            }
        }
        return formatter;
    }

}
