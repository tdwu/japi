package com.pm.biz.login.model.base;

import com.pm.japi.annotations.ApiNote;

import java.util.HashMap;
import java.util.Map;

public class ResultEntity<DATA> {

    //预定义号>=0代表业务正常<0代表业务异常
    public static Short CODE_SUCCESS = 0;
    public static Short CODE_FAIL = -1;
    //登陆异常
    public static Short CODE_AUTH_NONE = -10;//未登陆
    public static Short CODE_AUTH_TIMEOUT = -11;//登陆过期

    //权限异常
    public static Short CODE_NO_AUTH = -20;//无权限

    //业务需要小于-200，或采用默认的-1


    /**
     * 状态
     */
    @ApiNote("状态code：>=0代表业务正常，<0代表业务异常")
    private int code = CODE_SUCCESS;
    /**
     * 消息
     */
    private String message;
    /**
     * 数据
     */
    private DATA data;

    private ResultEntity() {
    }

    private ResultEntity(DATA data, String message, int code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    private ResultEntity(String message, int code) {
        this.message = message;
        this.code = code;
    }

    private ResultEntity(DATA data, int code) {
        this.data = data;
        this.code = code;
    }

    private ResultEntity(DATA data) {
        this.data = data;
    }

    private ResultEntity(String message) {
        this.message = message;
    }

    public ResultEntity(DATA data, String message) {
        this.data = data;
        this.message = message;
    }


    public static <DATA> ResultEntity<DATA> create(DATA data) {
        return new ResultEntity<>(data);
    }

    public static <DATA> ResultEntity<DATA> create(DATA data, int code) {
        return new ResultEntity<>(data, code);
    }

    public static <DATA> ResultEntity<DATA> create(String msg, int code) {
        return new ResultEntity<>(msg, code);
    }

    public static <DATA> ResultEntity<DATA> create(DATA data, String msg, int code) {
        return new ResultEntity<>(data, msg, code);
    }

    public static <DATA> ResultEntity<DATA> create(String msg) {
        return new ResultEntity<>(msg);
    }

    public static <DATA> ResultEntity<DATA> create(DATA data, String msg) {
        return new ResultEntity<>(data, msg);
    }

    public static <DATA> ResultEntity<DATA> create() {
        return new ResultEntity<>();
    }




    public static ResultEntity success() {
        return ResultEntity.create("成功", ResultEntity.CODE_SUCCESS);
    }

    public static ResultEntity success(int code) {
        return ResultEntity.create("成功", code);
    }


    public static <DATA> ResultEntity<DATA> success(String message) {
        return ResultEntity.create(null, message, ResultEntity.CODE_SUCCESS);
    }


    public static <DATA> ResultEntity<DATA> success(String message, int code) {
        return ResultEntity.create(null, message, code);
    }

    public static <DATA> ResultEntity<DATA> success(DATA data) {
        return ResultEntity.create(data, "成功", ResultEntity.CODE_SUCCESS);
    }

    public static <DATA> ResultEntity<DATA> success(DATA data, String message) {
        return ResultEntity.create(data, message, ResultEntity.CODE_SUCCESS);
    }


    public static ResultEntity failed() {
        return ResultEntity.create("失败", ResultEntity.CODE_FAIL);
    }

    public static ResultEntity failed(int code) {
        return ResultEntity.create("失败", code);
    }

    public static <DATA> ResultEntity<DATA> failed(String message) {
        return ResultEntity.create(null, message, ResultEntity.CODE_FAIL);
    }

    public static <DATA> ResultEntity<DATA> failed(String message, int code) {
        return ResultEntity.create(null, message, code);
    }


    public static <DATA> ResultEntity<DATA> failed(DATA data) {
        return ResultEntity.create(data, "成功", ResultEntity.CODE_FAIL);
    }

    public static <DATA> ResultEntity<DATA> failed(DATA data, String message) {
        return ResultEntity.create(data, message, ResultEntity.CODE_FAIL);
    }


    public static <DATA> ResultEntity<DATA> failed(DATA data, String message, int code) {
        return ResultEntity.create(data, message, code);
    }


    public int getCode() {
        return code;
    }

    public ResultEntity<DATA> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultEntity<DATA> setMessage(String message) {
        this.message = message;
        return this;
    }

    public DATA getData() {
        return data;
    }

    public ResultEntity<DATA> setData(DATA data) {
        this.data = data;
        return this;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", getData());
        map.put("code", getCode());
        map.put("message", getMessage());
        return map;
    }


}
