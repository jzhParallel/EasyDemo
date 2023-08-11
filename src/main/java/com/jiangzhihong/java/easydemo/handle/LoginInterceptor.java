package com.jiangzhihong.java.easydemo.handle;

import com.jiangzhihong.java.easydemo.mapper.UserMapper;
import com.jiangzhihong.java.easydemo.util.JWTUtil;
import io.jsonwebtoken.Claims;
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
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        // 验证Token的有效性
        if (token == null) return false;
        Claims checkedToken = JWTUtil.checkToken(token);
        if (checkedToken == null) return false;
        int uid = (int) checkedToken.get("userId");
        if (userMapper.selectByUid(uid) == null) return false;
        System.out.print("uid为" + uid + "的用户请求已通过拦截器...");
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
