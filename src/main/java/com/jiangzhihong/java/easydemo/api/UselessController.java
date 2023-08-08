package com.jiangzhihong.java.easydemo.api;

import com.jiangzhihong.java.easydemo.model.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @program: EasyDemo
 * @description: 无用的API
 * @author: jiangzhihong
 * @create: 2023-08-08 14:30
 **/
@RestController
@RequestMapping("/api/useless")
public class UselessController {

    @GetMapping("/hello/{name}")
    public Result hello(@PathVariable("name") String name) {
        return Result.success("hello " + name + "!");
    }

    @PostMapping("/and")
    public Result and(@RequestParam("a") int num1, @RequestParam("b") int num2) {
        return Result.success(num1 + num2);
    }
}
