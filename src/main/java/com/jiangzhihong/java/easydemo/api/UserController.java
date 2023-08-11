package com.jiangzhihong.java.easydemo.api;

import com.jiangzhihong.java.easydemo.model.Result;
import com.jiangzhihong.java.easydemo.model.params.LoginParams;
import com.jiangzhihong.java.easydemo.model.vo.UserVo;
import com.jiangzhihong.java.easydemo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @program: EasyDemo
 * @description: 用户功能API接口
 * @author: jiangzhihong
 * @create: 2023-08-07 14:00
 **/
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginParams params) {
        String account = params.getAccount();
        String password = params.getPassword();
        UserVo logined = userService.login(account, password);
        if (logined != null) {
            return Result.success(logined);
        }
        return Result.fail(501, "登录失败！");
    }

    @PostMapping("/register")
    public Result register(@RequestBody LoginParams params) {
        String account = params.getAccount();
        String password = params.getPassword();
        UserVo registered = null;
        registered = userService.register(account, password);
        if (registered != null) {
            return Result.success(registered);
        }
        return Result.fail(502, "注册失败！");
    }


    @PostMapping("/logout")
    public Result logout(@RequestHeader("Authorization") String token) {
        boolean flag = userService.logout(token);
        if (flag) return Result.success();
        else return Result.fail(503, "登出失败！");
    }
}
