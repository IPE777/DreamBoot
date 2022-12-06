package com.wangyuhang.dream_boot.controller;


import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Goods;
import com.wangyuhang.dream_boot.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/getGoods/{start}/{end}")
    public Result selectAllGoods(@PathVariable("start") Integer start, @PathVariable("end") Integer end){
        return goodsService.selectAllGoods(start,end);
    }

    @GetMapping("/selectAll")
    public Result selectAll(){
        return goodsService.selectAll();
    }

    // 根据id查找
    @GetMapping("/findById/{id}")
    public Result selectById(@PathVariable("id") Integer id){
        return goodsService.selectById(id);
    }

    // 根据姓名查找
    @GetMapping("/findByName")
    public Result selectByName(@RequestParam(name="name",  required = true) String name){
        return goodsService.selectByName(name);
    }

    // 添加用户并保存
    @PostMapping("/add")
    public Result addGoods(@RequestBody Goods goods){
        return goodsService.addGoods(goods);
    }

    // 更新用户
    @PutMapping("/updateById")
    public Result updateById( @RequestBody Goods goods){
        return goodsService.updateGoods(goods);
    }

    // 删除用户
    @GetMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") int id){
        return goodsService.deleteById(id);
    }

}
