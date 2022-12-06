package com.wangyuhang.dream_boot.controller;

import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.User;
import com.wangyuhang.dream_boot.mapper.UserMapper;
import com.wangyuhang.dream_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/hello")
    public String Hello(){
        return "hello";
    }

    @GetMapping("/getUser/{start}/{end}")
    public Result selectAllUser(@PathVariable("start") Integer start, @PathVariable("end") Integer end){
        return userService.selectAllUser(start,end);
    }

    // 根据id查找
    @GetMapping("/findById/{id}")
    public Result selectById(@PathVariable("id") Integer id){
        return userService.selectById(id);
    }

    // 根据姓名查找
    @GetMapping("/findByName")
    public Result selectByName(@RequestParam(name="name",  required = true) String name){
       return userService.selectByName(name);
    }

    // 添加用户并保存
    @PostMapping("/add")
    public Result addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    // 更新用户
    @PutMapping("/updateById")
    public Result updateById( @RequestBody User user){
        return userService.updateUser(user);
    }

    // 删除用户
    @GetMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") int id){
        return userService.deleteById(id);
    }
}
