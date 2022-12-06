package com.wangyuhang.dream_boot.controller;


import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.User;

import com.wangyuhang.dream_boot.realm.UserToken;
import com.wangyuhang.dream_boot.service.UserService;
import com.wangyuhang.dream_boot.vo.LoginType;
import org.apache.shiro.SecurityUtils;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;



@RestController
@RequestMapping("/end")
public class EndLoginController {

    @Autowired
    private UserService userService;

    private static final String USER_LOGIN_TYPE = LoginType.USER.toString();

    @PostMapping("/api/register")
    public Result register(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);

        boolean exist = userService.existUser(username);
        if (exist) {
            String message = "用户名已被使用";
            return Result.error(message);
        }


        // 生成盐,默认长度 16 位
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        // 设置 hash 算法迭代次数
        int times = 2;
        // 得到 hash 后的密码
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
        // 存储用户信息，包括 salt 与 hash 后的密码
        user.setSalt(salt);
        user.setPassword(encodedPassword);
        userService.addUser(user);

        return Result.ok(user);
    }

    @PostMapping(value = "/api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {
        Subject subject = SecurityUtils.getSubject();
        UserToken userToken = new UserToken(requestUser.getUsername(),requestUser.getPassword(),USER_LOGIN_TYPE);
        try {
            User user = userService.findByUserName(requestUser.getUsername());
            if (user == null){   // 这里有问题 这个判断是否存在应该从realm中获取
                return Result.error("用户不存在");
            }else {
                subject.login(userToken);
                return Result.ok(requestUser.getUsername());
            }

        } catch (AuthenticationException e) {
            String message = "账号密码错误";
            return Result.error(message);
        }

    }

    @GetMapping("api/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        String message = "成功登出";
        return Result.ok(message);
    }


    @GetMapping(value = "api/authentication")
    public String authentication(){
        return "身份认证成功";
    }


    @GetMapping(value = "api/getUser")
    public Result getUser(@RequestParam(name="username",  required = true) String username){
        return Result.ok(userService.findByUserName(username));
    }
}
