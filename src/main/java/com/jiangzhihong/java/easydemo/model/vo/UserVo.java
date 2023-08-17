package com.jiangzhihong.java.easydemo.model.vo;

import lombok.Data;

/**
 * @program: EasyDemo
 * @description: 用户vo类 TODO
 * @author: jiangzhihong
 * @create: 2023-08-10 10:01
 **/

@Data
public class UserVo {

    private Long uid;

    private String account;

    private String token;
}
