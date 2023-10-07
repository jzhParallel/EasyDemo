package com.jiangzhihong.java.easydemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: EasyDemo
 * @description: 返回结果类 TODO
 * @author: jiangzhihong
 * @create: 2023-08-07 14:04
 **/

@Schema(description = "统一返回结果")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    @Schema(description = "调用是否成功")
    private boolean success;

    @Schema(description = "返回结果码")
    private Integer code;

    @Schema(description = "返回结果信息")
    private String msg;

    @Schema(description = "返回数据")
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

    public static Result fail(ErrorCode errorCode) {
        if (errorCode == null) errorCode = ErrorCode.SPECIAL_ERROR;
        return fail(errorCode.getCode(), errorCode.getMsg());
    }
}
