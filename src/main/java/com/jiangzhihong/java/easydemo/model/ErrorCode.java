package com.jiangzhihong.java.easydemo.model;

import lombok.Getter;

/**
 * @ClassName ErrorCode
 * @Description
 * @date 2023/10/7 14:49
 * @Version 1.0
 */

@Getter
public enum ErrorCode {

    VALID_ERROR(10001, "校验参数错误"),
    NOT_EMPTY(10002, "重要参数不能为空"),
    LOGIN_ERROR(20001, "登录失败"),
    REGISTER_ERROR(20002, "注册失败"),
    LOGOUT_ERROR(20003, "登出失败"),
    NO_LOGIN(20004, "用户未登录"),
    SPECIAL_ERROR(-1, "特殊错误");

    private int code;

    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
