package com.jiangzhihong.java.easydemo.model.params;

import lombok.Data;

/**
 * @ClassName UpdateUserParams
 * @Description 用户信息修改参数
 * @date 2023/10/7 16:50
 * @Version 1.0
 */

@Data
public class UpdateUserParams {

    private String account;

    private String name;

    private String password;
}
