package com.song.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "RESFul统一返回模型", description = "RESFul统一返回模型")
public class Result<T> implements Serializable {
    @ApiModelProperty("状态代码")
    private int code;
    @ApiModelProperty("响应数据")
    private T data;
    @ApiModelProperty("提示消息")
    private String msg;

    public Result(T data) {
        this.data = data;
        this.code = 200;
    }

    public Result(T data, String msg, int code) {
        this.data = data;
        this.msg = msg;
        this.code = code;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> error(T o, String msg) {
        return new Result<>(o, msg, 500);
    }

    public static <T> Result<T> error(T o, String msg, int code) {
        return new Result<>(o, msg, code);
    }

    public static <T> Result<T> success(int code, String msg, T data) {
        return new Result<>(data, msg, code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
