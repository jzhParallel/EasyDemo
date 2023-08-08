package com.jiangzhihong.java.easydemo.api;

import com.jiangzhihong.java.easydemo.model.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * @program: EasyDemo
 * @description: 无用的API
 * @author: jiangzhihong
 * @create: 2023-08-08 14:30
 **/
@Tag(name = "UselessController", description = "EasyDemo-无用服务-API接口")
@RestController
@RequestMapping("/api/useless")
public class UselessController {

    @Operation(summary = "hello", description = "一个传入名字和你打招呼的接口", method = "GET")
    @GetMapping("/hello/{name}")
    public Result hello(@PathVariable("name") String name) {
        return Result.success("hello " + name + "!");
    }

    @Operation(summary = "sum", description = "加法接口，将传入参数a、b相加", method = "POST")
    @PostMapping("/and")
    public Result and(@RequestParam("a") int num1, @RequestParam("b") int num2) {
        return Result.success(num1 + num2);
    }
}
