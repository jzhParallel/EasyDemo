package com.jiangzhihong.java.easydemo.model;

import lombok.Data;

/**
 * @program: EasyDemo
 * @description: 登录注册参数
 * @author: jiangzhihong
 * @create: 2023-08-07 15:20
 **/
@Data
public class LoginParam {
    private String account;

    private String password;
}
