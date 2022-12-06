package com.wangyuhang.dream_boot.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {


}
