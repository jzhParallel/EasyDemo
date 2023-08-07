package com.jiangzhihong.java.easydemo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program: lucky-draw
 * @Package: com.jiangzhihong.java.luckyDraw.config
 * @description: mybatisPlus的配置类
 * @author: jzh
 * @create: 2023-03-24 14:39
 **/

@Configuration
@MapperScan("com.jiangzhihong.java.easydemo.mapper")
public class MybatisConfig {

}
