package com.song.utils;

/**
 * @author XiaoSong
 * @date 2019-12-18 14:32
 */
public class Constant {
    /**
     * 操作失败状态码
     */
    public final static int RESULT_STATE_FAIL = -1;
    /**
     * 操作成功状态码
     */
    public final static int RESULT_STATE_SUCCESS = 1;
    /**
     * 返回到前端map状态key
     */
    public final static String RESULT_STATE_KEY = "state";
    /**
     * 返回到前端map数据key
     */
    public static final String RESULT_DATA_KEY = "data";
    /**
     * 返回到前端map提示信息key
     */
    public static final String RESULT_STATE_MSG_KEY = "msg";
    /**
     * 数据库字符串长度：100
     */
    public final static int DATABASE_VARCHAR_LENGTH_100 = 100;
    /**
     * 数据库字段过长异常信息
     */
    public static final Object DATABASE_LENGTH_ERROR_MSG = "输入的内容过长";
    /**
     * 数据库字符串长度：1000
     */
    public static final int DATABASE_VARCHAR_LENGTH_8000 = 8000;
}
