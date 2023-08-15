package com.jiangzhihong.java.easydemo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @program: EasyDemo
 * @description: 用户类
 * @author: jiangzhihong
 * @create: 2023-08-06 09:36
 **/

@TableName("ed_user")
@Data
public class User {

    private Integer uid;

    private String account;

    private String password;
}
