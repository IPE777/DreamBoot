package com.wangyuhang.dream_boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Content;
import com.wangyuhang.dream_boot.entity.Customer;
import com.wangyuhang.dream_boot.entity.User;
import com.wangyuhang.dream_boot.mapper.ContentMapper;
import com.wangyuhang.dream_boot.mapper.CustomerMapper;
import com.wangyuhang.dream_boot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public Result selectAllCustomer(Integer start, Integer end) {
        Page<Customer> page = new Page<>(start,end);
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        return Result.ok(customerMapper.selectPage(page,queryWrapper));
    }

    @Override
    public Result selectById(Integer id) {
        return Result.ok(customerMapper.selectById(id));
    }

    @Override
    public Result selectByName(String name) {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);

        List<Customer> list = new ArrayList<>();
        // 判断是否有输入搜索 没有则返回全部 待完善
        if (!Objects.equals(name, "")){
            list = customerMapper.selectList(queryWrapper);
        }

        return Result.ok(list);
    }

    @Override
    public Result addCustomer(Customer customer) {

        int result = customerMapper.insert(customer);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("error");
        }
    }

    @Override
    public Result updateCustomer(Customer customer) {
        int result = customerMapper.updateById(customer);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("err");
        }
    }

    @Override
    public Result deleteById(Integer id) {
        int result = customerMapper.deleteById(id);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("err");
        }
    }

    @Override
    public Boolean existCustomer(String username) {

        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        Customer customer = customerMapper.selectOne(queryWrapper);
        return null != customer;
    }

    @Override
    public Customer findByUserName(String username) {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return customerMapper.selectOne(queryWrapper);
    }

    @Override
    public Result selectMyContent(Integer start, Integer end, Integer customerId) {
        Page<Content> page = new Page<>(start,end);
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_id",customerId);
        return Result.ok(contentMapper.selectPage(page,queryWrapper));
    }
}
