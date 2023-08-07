package com.jiangzhihong.java.easydemo.service;

import com.jiangzhihong.java.easydemo.model.User;

/**
 * @program: EasyDemo
 * @description: 用户服务 TODO
 * @author: jiangzhihong
 * @create: 2023-08-06 10:19
 **/
public interface UserService {

    User login(String account, String password);

    Integer register(String account, String password);
}
