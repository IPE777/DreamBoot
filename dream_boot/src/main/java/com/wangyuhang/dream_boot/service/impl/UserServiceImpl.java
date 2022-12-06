package com.wangyuhang.dream_boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Customer;
import com.wangyuhang.dream_boot.entity.User;
import com.wangyuhang.dream_boot.mapper.UserMapper;
import com.wangyuhang.dream_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public Result selectAllUser(Integer start, Integer end) {
        Page<User> page = new Page<>(start,end);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        return Result.ok(userMapper.selectPage(page,queryWrapper));
    }

    @Override
    public Result selectById(Integer id) {
        return Result.ok(userMapper.selectById(id));
    }


    @Override
    public Result selectByName(String name) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);

        List<User> list = new ArrayList<>();
        // 判断是否有输入搜索 没有则返回全部 待完善
        if (!Objects.equals(name, "")){
            list = userMapper.selectList(queryWrapper);
        }

        return Result.ok(list);
    }


    @Override
    public Result addUser(User user) {
        int result = userMapper.insert(user);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("error");
        }
    }

    @Override
    public Result updateUser(User user) {
        int result = userMapper.updateById(user);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("err");
        }
    }

    @Override
    public Result deleteById(Integer id) {
        int result = userMapper.deleteById(id);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("err");
        }
    }

    @Override
    public Boolean existUser(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = userMapper.selectOne(queryWrapper);
        return null != user;
    }

    @Override
    public User findByUserName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return userMapper.selectOne(queryWrapper);
    }
}
