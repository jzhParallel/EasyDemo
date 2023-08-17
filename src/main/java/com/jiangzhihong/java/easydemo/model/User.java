package com.jiangzhihong.java.easydemo.model;

import lombok.Data;

/**
 * @program: EasyDemo
 * @description: 用户类
 * @author: jiangzhihong
 * @create: 2023-08-06 09:36
 **/

@Data
public class User {

    private Long uid;

    private String account;

    private String password;
}
