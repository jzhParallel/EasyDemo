package com.jiangzhihong.java.easydemo.config;

import com.jiangzhihong.java.easydemo.handle.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @program: EasyDemo
 * @description: 拦截器配置
 * @author: jiangzhihong
 * @create: 2023-08-11 14:22
 **/
@PropertySource("classpath:application.yml")
public class InterceptorConfig implements WebMvcConfigurer {

    @Value("${path.intercept}")
    private List<String> interceptPath;

    @Value("${path.exclude}")
    private List<String> excludePath;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration loginIR = registry.addInterceptor(new LoginInterceptor());
        loginIR.addPathPatterns(interceptPath);//拦截
        loginIR.excludePathPatterns(excludePath);//放行
    }
}
