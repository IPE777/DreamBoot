package com.wangyuhang.dream_boot.controller;



import com.wangyuhang.dream_boot.common.Result;

import com.wangyuhang.dream_boot.entity.GoodsOrder;
import com.wangyuhang.dream_boot.service.GoodsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goodsOrder")
public class GoodsOrderController {

    @Autowired
    private GoodsOrderService goodsOrderService;

    @GetMapping("/getGoodsOrder/{start}/{end}")
    public Result selectAllGoodsOrder(@PathVariable("start") Integer start, @PathVariable("end") Integer end){
        return goodsOrderService.selectAllGoodsOrder(start,end);
    }

    @GetMapping("/getMyGoodsOrder/{start}/{end}/{customer_id}")
    public Result selectMyGoodsOrder(@PathVariable("start") Integer start, @PathVariable("end") Integer end,@PathVariable("customer_id") Integer customer_id){
        return goodsOrderService.selectMyGoodsOrder(start,end,customer_id);
    }

    // 根据id查找
    @GetMapping("/findById/{id}")
    public Result selectById(@PathVariable("id") Integer id){
        return goodsOrderService.selectById(id);
    }

    // 根据姓名查找
    @GetMapping("/findByName")
    public Result selectByName(@RequestParam(name="name",  required = true) String name){
        return goodsOrderService.selectByName(name);
    }

    // 添加并保存
    @PostMapping("/add")
    public Result addGoodsOrder(@RequestBody GoodsOrder goodsOrder){
        return goodsOrderService.addGoodsOrder(goodsOrder);
    }

    // 更新用户
    @PutMapping("/updateById")
    public Result updateById( @RequestBody GoodsOrder goodsOrder){
        return goodsOrderService.updateGoodsOrder(goodsOrder);
    }

    // 删除用户
    @GetMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") int id){
        return goodsOrderService.deleteById(id);
    }

}
