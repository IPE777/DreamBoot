package com.wangyuhang.dream_boot.controller;

import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.GoodsOrder;
import com.wangyuhang.dream_boot.entity.Order;
import com.wangyuhang.dream_boot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getOrder/{start}/{end}")
    public Result selectAllGoods(@PathVariable("start") Integer start, @PathVariable("end") Integer end){
        return orderService.selectAllOrder(start,end);
    }

    // 根据id查找
    @GetMapping("/findById/{id}")
    public Result selectById(@PathVariable("id") Integer id){
        return orderService.selectById(id);
    }

    // 根据姓名查找
    @GetMapping("/findByName")
    public Result selectByName(@RequestParam(name="name",  required = true) String name){
        return orderService.selectByName(name);
    }

    // 添加用户并保存
    @PostMapping("/add")
    public Result addGoods(@RequestBody Order order){
        return orderService.addOrder(order);
    }

    // 更新用户
    @PutMapping("/updateById")
    public Result updateById( @RequestBody Order order){
        return orderService.updateOrder(order);
    }

    // 删除用户
    @GetMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") int id){
        return orderService.deleteById(id);
    }

}
