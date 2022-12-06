package com.wangyuhang.dream_boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Customer;
import com.wangyuhang.dream_boot.entity.User;

public interface UserService extends IService<User> {

    Result selectAllUser(Integer start,Integer end);

    Result selectById(Integer id);

    Result selectByName(String name);

    Result addUser(User user);

    Result updateUser(User user);

    Result deleteById(Integer id);

    Boolean existUser(String username);

    User findByUserName(String username);


}
