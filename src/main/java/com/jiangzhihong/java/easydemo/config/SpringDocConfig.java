package com.jiangzhihong.java.easydemo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @description: SpringDoc配置类
 * @author: jiangzhihong
 * @create: 2023-08-01 15:42
 **/
@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI mallTinyOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("EasyDemo-API接口文档")
                        .description("EasyDemo-API接口文档，随便看看不要当真")
                        .contact(new Contact().name("被风吹散").email("601079657@QQ.com"))
                        .version("v0.0.2"));
    }
}
