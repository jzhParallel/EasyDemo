package com.jiangzhihong.java.easydemo.service;

import com.jiangzhihong.java.easydemo.model.User;
import com.jiangzhihong.java.easydemo.model.vo.UserVo;

/**
 * @program: EasyDemo
 * @description: 用户服务
 * @author: jiangzhihong
 * @create: 2023-08-06 10:19
 **/
public interface UserService {

    UserVo login(String account, String password);

    UserVo register(String account, String password);

    boolean logout(String token);

    UserVo current(String token);

    int update(User user, String token);

    int ban(String token, String password);
}
