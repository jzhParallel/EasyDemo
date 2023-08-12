package com.jiangzhihong.java.easydemo.service.impl;

import com.jiangzhihong.java.easydemo.mapper.UserMapper;
import com.jiangzhihong.java.easydemo.model.User;
import com.jiangzhihong.java.easydemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: EasyDemo
 * @description: 用户服务实现类
 * @author: jiangzhihong
 * @create: 2023-08-06 10:23
 **/

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User login(String account, String password) {
        log.debug("用户登录中……账号是" + account);
        return userMapper.selectByAccountAndPassword(account, password);
    }

    @Override
    public void register(String account, String password) {
        userMapper.insertUser(account, password);
    }
}
