package com.jiangzhihong.java.easydemo.model;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * @program: EasyDemo
 * @description: 用户类
 * @author: jiangzhihong
 * @create: 2023-08-06 09:36
 **/

@TableName("user")
@Data
public class User {

    private Integer uid;

    private String account;

    private String password;

    @TableLogic(value = "0", delval = "1")
    private int deleted;

    @Version
    private int version;
}
