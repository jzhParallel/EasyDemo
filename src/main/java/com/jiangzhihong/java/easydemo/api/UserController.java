package com.jiangzhihong.java.easydemo.api;

import com.jiangzhihong.java.easydemo.model.LoginParam;
import com.jiangzhihong.java.easydemo.model.Result;
import com.jiangzhihong.java.easydemo.model.User;
import com.jiangzhihong.java.easydemo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: EasyDemo
 * @description: 用户功能API接口 TODO
 * @author: jiangzhihong
 * @create: 2023-08-07 14:00
 **/
@Tag(name = "UserController", description = "EasyDemo-用户服务-API接口")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    UserService userService;

    @Operation(summary = "login", description = "用户登录接口，使用账密登录", method = "POST")
//    @ApiResponses(//为单个接口配置多个返回码描述
//            @ApiResponse(responseCode = "400",description = "Bad Request")
//    )
    @PostMapping("/login")
    public Result login(@RequestBody LoginParam param) {
        String account = param.getAccount();
        String password = param.getPassword();
        User logined = userService.login(account, password);
        if (logined != null) {
            return Result.success(logined);
        }
        return Result.fail(501, "登录失败！");
    }

    @Operation(summary = "register", description = "用户注册接口，采用账号密码注册", method = "POST")
    @PostMapping("/register")
    public Result register(@RequestBody LoginParam param) {
        String account = param.getAccount();
        String password = param.getPassword();
        try {
            userService.register(account, password);
        } catch (Exception e) {
            System.out.println(e);
            return Result.fail(502, "注册失败！");
        }
        return Result.success();
    }
}
