package com.jiangzhihong.java.easydemo.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: EasyDemo
 * @description: MyBatisPlus配置类 TODO
 * @author: jiangzhihong
 * @create: 2023-08-15 13:57
 **/

@Configuration
public class MyBatisPlusConfig {

    /**
     * 生成MyBatisPlus拦截器
     */
    @Bean
    public MybatisPlusInterceptor interceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());//添加分页拦截器
        return mybatisPlusInterceptor;
    }

}
