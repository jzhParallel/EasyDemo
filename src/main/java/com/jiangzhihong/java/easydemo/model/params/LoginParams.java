package com.jiangzhihong.java.easydemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: EasyDemo
 * @description: 登录注册参数
 * @author: jiangzhihong
 * @create: 2023-08-07 15:20
 **/
@Schema(description = "登录参数类")
@Data
public class LoginParams {

    @Schema(description = "登录的账号", required = true)
    @NotNull
    private String account;

    @Schema(description = "输入的密码", required = true)
    @NotNull
    private String password;
}
