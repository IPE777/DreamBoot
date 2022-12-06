package com.wangyuhang.dream_boot.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.GoodsOrder;
import com.wangyuhang.dream_boot.entity.Order;

public interface OrderService extends IService<Order> {

    Result selectAllOrder(Integer start, Integer end);

    Result selectById(Integer id);

    Result selectByName(String name);

    Result addOrder(Order order);

    Result updateOrder(Order order);

    Result deleteById(Integer id);
}
