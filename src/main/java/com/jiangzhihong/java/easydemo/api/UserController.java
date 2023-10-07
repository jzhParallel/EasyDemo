package com.jiangzhihong.java.easydemo.api;

import com.jiangzhihong.java.easydemo.model.ErrorCode;
import com.jiangzhihong.java.easydemo.model.LoginParams;
import com.jiangzhihong.java.easydemo.model.Result;
import com.jiangzhihong.java.easydemo.model.vo.UserVo;
import com.jiangzhihong.java.easydemo.service.UserService;
import com.jiangzhihong.java.easydemo.util.StringUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @program: EasyDemo
 * @description: 用户功能API接口
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
    public Result login(@RequestBody @Valid LoginParams params, BindingResult errors) {
        if (errors != null) return Result.fail(ErrorCode.VALID_ERROR);
        String account = params.getAccount();
        String password = params.getPassword();
        UserVo logined = userService.login(account, password);
        if (logined != null) {
            return Result.success(logined);
        }
        return Result.fail(ErrorCode.LOGIN_ERROR);
    }

    @Operation(summary = "register", description = "用户注册接口，采用账号密码注册", method = "POST")
    @PostMapping("/register")
    public Result register(@RequestBody @Valid LoginParams params, BindingResult errors) {
        if (errors != null) return Result.fail(ErrorCode.VALID_ERROR);
        String account = params.getAccount();
        String password = params.getPassword();
        UserVo registered = userService.register(account, password);
        if (registered != null) {
            return Result.success(registered);
        }
        return Result.fail(ErrorCode.REGISTER_ERROR);
    }


    @PostMapping("/logout")
    public Result logout(@RequestHeader("Authorization") String token) {
        if (StringUtil.isBlank(token)) return Result.fail(ErrorCode.NOT_EMPTY);
        boolean flag = userService.logout(token);
        if (flag) return Result.success();
        else return Result.fail(ErrorCode.LOGOUT_ERROR);
    }

    @PostMapping("/current")
    public Result currentUser(@RequestHeader("Authorization") String token) {
        if (StringUtil.isBlank(token)) return Result.fail(ErrorCode.NOT_EMPTY);
        UserVo current = userService.current(token);
        if (current != null) return Result.success(current);
        else return Result.fail(ErrorCode.NO_LOGIN);
    }
}
