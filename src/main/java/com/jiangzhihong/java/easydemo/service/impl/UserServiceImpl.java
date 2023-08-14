package com.jiangzhihong.java.easydemo.service.impl;

import com.jiangzhihong.java.easydemo.mapper.UserMapper;
import com.jiangzhihong.java.easydemo.model.User;
import com.jiangzhihong.java.easydemo.model.vo.UserVo;
import com.jiangzhihong.java.easydemo.service.UserService;
import com.jiangzhihong.java.easydemo.util.JWTUtil;
import io.jsonwebtoken.Claims;
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

    /**
     * 登录流程：
     * 1.查找账密匹配的用户
     * 2.设置token
     * 3.返回vo类
     */
    @Override
    public User login(String account, String password) {
        log.debug("用户登录中……账号是" + account);
        return userMapper.selectByAccountAndPassword(account, password);
    public UserVo login(String account, String password) {
        User user = userMapper.selectByAccountAndPassword(account, password);
        UserVo userVo = null;
        if (user != null) {
            userVo = new UserVo();
            userVo.setAccount(user.getAccount());
            userVo.setToken(JWTUtil.createToken(user.getUid(), 1000 * 60 * 60 * 24));//token有效期一天
        }
        return userVo;
    }

    /**
     * 登录流程：
     * 1.新增用户
     * 2.设置token
     * 3.返回vo类
     */
    @Override
    public UserVo register(String account, String password) {
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        try {
            userMapper.insertUser(user);
        } catch (Exception e) {
            //出现问题就捕捉返回空值，目的是减少插入前查询的步骤。逻辑上是针对account列的唯一性约束，然而也可能发生其他错误。这是偷懒的做法，不推荐。
            return null;
        }
        UserVo userVo = new UserVo();
        userVo.setAccount(user.getAccount());
        userVo.setToken(JWTUtil.createToken(user.getUid(), 1000 * 60 * 60 * 24));//token有效期一天
        return userVo;
    }

    /**
     * 登入登出功能需要在数据库中记录状态
     * 如果每次登录登出都在MySQL数据库中写数据，可能会导致程序响应速度慢的问题
     * 一般的做法是在redis中记录用户相关的token，登入时加入、登出时山粗
     * 如果需要完整的登入登出代码，请参照redis分支或者finalLogin分支
     */
    @Override
    public boolean logout(String token) {
        Claims checkedToken = JWTUtil.checkToken(token);
        if (checkedToken == null) return false;
        int uid = (int) checkedToken.get("userId");
        User user = userMapper.selectByUid(uid);
        if (user == null) return false;
        else return true;
    }
}
