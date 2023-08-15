package com.jiangzhihong.java.easydemo.api;

import com.jiangzhihong.java.easydemo.model.LoginParam;
import com.jiangzhihong.java.easydemo.model.Result;
import com.jiangzhihong.java.easydemo.model.User;
import com.jiangzhihong.java.easydemo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: EasyDemo
 * @description: 用户功能API接口 TODO
 * @author: jiangzhihong
 * @create: 2023-08-07 14:00
 **/
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    UserService userService;

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

    @PostMapping("/register")
    public Result register(@RequestBody LoginParam param) {
        String account = param.getAccount();
        String password = param.getPassword();
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        try {
            userService.register(user);
        } catch (Exception e) {
            System.out.println(e);
            return Result.fail(502, "注册失败！");
        }
        return Result.success();
    }

    @GetMapping("/list")
    public Result list(@RequestParam("current") int current, @RequestParam("size") int size) {
        List<User> users = userService.listUserByPage(current, size);
        return Result.success(users);
    }
}
