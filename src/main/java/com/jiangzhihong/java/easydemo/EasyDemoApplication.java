package com.jiangzhihong.java.easydemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class EasyDemoApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(EasyDemoApplication.class, args);
            System.out.println("乁( ˙ ω˙乁)项目启动了耶，它能跑我就不用跑了咯。");
            log.trace("这是trace级日志");
            log.debug("这是debug级日志");
            log.info("这是info级日志");
            log.warn("这是warning级日志");
            log.error("这是error级日志");
        } catch (Exception e) {
            System.out.println("(￣^￣)ゞ报告！出现错误了！");
            log.error(e.toString());
        }
    }

}
