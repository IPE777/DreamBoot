package com.wangyuhang.dream_boot.controller;

import com.sun.xml.internal.txw2.output.ResultFactory;
import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Customer;

import com.wangyuhang.dream_boot.realm.UserToken;
import com.wangyuhang.dream_boot.service.CustomerService;
import com.wangyuhang.dream_boot.vo.LoginType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;



@RestController
@RequestMapping("/front")
public class FrontLoginController {

    @Autowired
    private CustomerService customerService;

    private static final String CUSTOMER_LOGIN_TYPE = LoginType.CUSTOMER.toString();
    @PostMapping("/api/register")
    @ResponseBody
    public Result register(@RequestBody Customer customer) {
        String username = customer.getUsername();
        String password = customer.getPassword();
        username = HtmlUtils.htmlEscape(username);
        customer.setUsername(username);

        boolean exist = customerService.existCustomer(username);
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
        customer.setSalt(salt);
        customer.setPassword(encodedPassword);


        customer.setName("回个人中心修改昵称");
        customer.setImage("https://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/12/02/9537751b-47ff-494d-b2d6-7d48ff0d4914.jpg");
        customer.setEmail("回个人中心修改邮箱");
        customer.setPhone("回个人中心修改手机号");
        customer.setAddress("回个人中心修改地址");


        customerService.addCustomer(customer);

        return Result.ok(customer);
    }

    @PostMapping(value = "/api/login")
    @ResponseBody
    public Result login(@RequestBody Customer requestCustomer) {
        Subject subject = SecurityUtils.getSubject();
        UserToken userToken = new UserToken(requestCustomer.getUsername(),requestCustomer.getPassword(),CUSTOMER_LOGIN_TYPE);
        try{
            Customer customer = customerService.findByUserName(requestCustomer.getUsername());
            if (customer == null){
                return Result.error("用户不存在");
            }else {
                subject.login(userToken);
                return Result.ok(requestCustomer.getUsername());
            }
        } catch (AuthenticationException e) {
            String message = "账号密码错误";
            return Result.error(message);
        }
    }

    @ResponseBody
    @GetMapping("api/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        String message = "成功登出";
        return Result.ok(message);
    }

    @ResponseBody
    @GetMapping(value = "api/authentication")
    public String authentication(){
        return "身份认证成功";
    }

    @ResponseBody
    @GetMapping(value = "api/getUser")
    public Result getUser(@RequestParam(name="username",  required = true) String username){
        return Result.ok(customerService.findByUserName(username));
    }





}
