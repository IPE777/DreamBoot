package com.wangyuhang.dream_boot.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.GoodsOrder;
import com.wangyuhang.dream_boot.entity.Order;
import com.wangyuhang.dream_boot.mapper.OrderMapper;
import com.wangyuhang.dream_boot.service.OrderService;
import com.wangyuhang.dream_boot.utils.SnowFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Override
    public Result selectAllOrder(Integer start, Integer end) {
        Page<Order> page = new Page<>(start,end);
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        return Result.ok(orderMapper.selectPage(page,queryWrapper));
    }

    @Override
    public Result selectById(Integer id) {
        return Result.ok(orderMapper.selectById(id));
    }

    @Override
    public Result selectByName(String name) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_name",name);

        List<Order> list = new ArrayList<>();
        // 判断是否有输入搜索 没有则返回全部 待完善
        if (!Objects.equals(name, "")){
            list = orderMapper.selectList(queryWrapper);
        }

        return Result.ok(list);
    }

    @Override
    public Result addOrder(Order order) {
        SnowFlow snowFlow = new SnowFlow(1,1,1);
        String code = String.valueOf(snowFlow.nextId());
        order.setCode(code);
        int result = orderMapper.insert(order);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("error");
        }
    }

    @Override
    public Result updateOrder(Order order) {
        int result = orderMapper.updateById(order);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("err");
        }
    }

    @Override
    public Result deleteById(Integer id) {
        int result = orderMapper.deleteById(id);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("err");
        }
    }
}
