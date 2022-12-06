package com.wangyuhang.dream_boot.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangyuhang.dream_boot.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {
}
