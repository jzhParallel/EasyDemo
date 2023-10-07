package com.jiangzhihong.java.easydemo.handle;

import com.jiangzhihong.java.easydemo.mapper.UserMapper;
import com.jiangzhihong.java.easydemo.util.JWTUtil;
import com.jiangzhihong.java.easydemo.util.StringUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: EasyDemo
 * @description: 登录拦截器
 * @author: jiangzhihong
 * @create: 2023-08-11 14:25
 **/

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        // 验证Token的有效性
        if (StringUtil.isBlank(token)) return false;
        Claims checkedToken = JWTUtil.checkToken(token);
        if (checkedToken == null) return false;
        Long uid = (Long) checkedToken.get("userId");
        String userStr = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtil.isBlank(userStr)) return false;
        log.debug("【登陆拦截器】uid为{}的用户请求已通过拦截器...", uid);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 在请求完成后执行的代码
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 在请求完成后执行的代码
    }
}
