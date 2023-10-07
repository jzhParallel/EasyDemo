package com.jiangzhihong.java.easydemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jiangzhihong.java.easydemo.mapper.UserMapper;
import com.jiangzhihong.java.easydemo.model.User;
import com.jiangzhihong.java.easydemo.model.entity.UserEntity;
import com.jiangzhihong.java.easydemo.model.vo.UserVo;
import com.jiangzhihong.java.easydemo.service.UserService;
import com.jiangzhihong.java.easydemo.util.JWTUtil;
import com.jiangzhihong.java.easydemo.util.StringUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 登录流程：
     * 1.查找账密匹配的用户
     * 2.设置token
     * 3.将token和user对象以kv类型存入redis
     * 4.返回vo类
     */
    @Override
    public UserVo login(String account, String password) {
        log.debug("用户{}登录中……", account);
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getAccount, account);
        wrapper.eq(UserEntity::getPassword, password);
        UserEntity userEntity = userMapper.selectOne(wrapper);
        UserVo userVo = null;
        if (userEntity != null) {
            User user = new User();
            BeanUtils.copyProperties(userEntity, user);
            userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
            String token = JWTUtil.createToken(user.getUid(), 1000 * 60 * 60 * 24);//token有效期一天
            userVo.setToken(token);
            redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(user), 1, TimeUnit.DAYS);//有效期一天的token在redis中也存一天
            log.debug("用户{}登录成功", account);
        }
        return userVo;
    }

    /**
     * 登录流程：
     * 1.新增用户
     * 2.设置token
     * 3.将token和user对象以kv类型存入redis
     * 4.返回vo类
     */
    @Override
    public UserVo register(String account, String password) {
        log.debug("用户{}注册中……", account);
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        try {
            userMapper.insert(userEntity);
        } catch (Exception e) {
            //出现问题就捕捉返回空值，目的是减少插入前查询的步骤。逻辑上是针对account列的唯一性约束，然而也可能发生其他错误。这是偷懒的做法，不推荐。
            log.error("用户{}注册失败：{}", account, e.getMessage());
            return null;
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        String token = JWTUtil.createToken(user.getUid(), 1000 * 60 * 60 * 24);//token有效期一天
        userVo.setToken(token);
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(user), 1, TimeUnit.DAYS);//有效期一天的token在redis中也存一天
        log.debug("用户{}注册成功", account);
        return userVo;
    }

    /**
     * 登出流程：
     * 1. 先检查token是否合法，不合法就返回false表示token错误
     * 2. 如果token合法则删除redis中的token，表示登出成功
     */
    @Override
    public boolean logout(String token) {
        Claims claims = JWTUtil.checkToken(token);
        if (claims == null) return false;
        //删除redis中的token，表示退出成功
        redisTemplate.delete("TOKEN_" + token);
        Long id = (Long) claims.get("userId");
        log.debug("用户id:{}的用户登出成功", id);
        return true;
    }

    @Override
    public UserVo current(String token) {
        Claims claims = JWTUtil.checkToken(token);
        if (claims == null) return null;
        String userStr = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtil.isBlank(userStr)) return null;
        User user = JSON.parseObject(userStr, User.class);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        log.debug("当前用户{}", user.getAccount());
        return userVo;
    }
}
