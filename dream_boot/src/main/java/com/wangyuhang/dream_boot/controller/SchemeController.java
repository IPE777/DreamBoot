package com.wangyuhang.dream_boot.controller;


import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Scheme;
import com.wangyuhang.dream_boot.service.SchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scheme")
public class SchemeController {

    @Autowired
    private SchemeService schemeService;

    @GetMapping("/getScheme/{start}/{end}")
    public Result selectAllGoods(@PathVariable("start") Integer start, @PathVariable("end") Integer end){
        return schemeService.selectAllScheme(start,end);
    }

    // 根据id查找
    @GetMapping("/findById/{id}")
    public Result selectById(@PathVariable("id") Integer id){
        return schemeService.selectById(id);
    }

    // 根据姓名查找
    @GetMapping("/findByName")
    public Result selectByName(@RequestParam(name="name",  required = true) String name){
        return schemeService.selectByName(name);
    }

    // 添加用户并保存
    @PostMapping("/add")
    public Result addGoods(@RequestBody Scheme scheme){
        return schemeService.addScheme(scheme);
    }

    // 更新用户
    @PutMapping("/updateById")
    public Result updateById( @RequestBody Scheme scheme){
        return schemeService.updateScheme(scheme);
    }

    // 删除用户
    @GetMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") int id){
        return schemeService.deleteById(id);
    }

}
