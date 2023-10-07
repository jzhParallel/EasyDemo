package com.jiangzhihong.java.easydemo.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * @program: EasyDemo
 * @description: 用户实体类
 * @author: jiangzhihong
 * @create: 2023-08-16 16:00
 **/

@TableName("user")
@Data
public class UserEntity {

    //主键采用雪花id
    @TableId(type = IdType.ASSIGN_ID)
    private Long uid;

    private String name;

    private String account;

    private String password;

    @TableLogic(value = "0", delval = "1")
    private Integer deleted;

    @Version
    private int version;
}
