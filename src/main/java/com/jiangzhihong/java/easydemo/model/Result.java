package com.jiangzhihong.java.easydemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: EasyDemo
 * @description: 返回结果类 TODO
 * @author: jiangzhihong
 * @create: 2023-08-07 14:04
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private boolean success;

    private Integer code;

    private String msg;

    private Object data;

    public static Result success(Object data) {
        return new Result(true, 200, "success", data);
    }

    public static Result success() {
        return success(null);
    }

    public static Result fail(Integer code, String msg) {
        return new Result(false, code, msg, null);
    }

}
