package com.wangyuhang.dream_boot.controller;


import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Message;
import com.wangyuhang.dream_boot.entity.Order;
import com.wangyuhang.dream_boot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;


    @GetMapping("/getMessage/{start}/{end}")
    public Result selectAllMessage(@PathVariable("start") Integer start, @PathVariable("end") Integer end){
        return messageService.selectAllMessage(start,end);
    }

    @GetMapping("/getMyMessage/{start}/{end}/{master_id}")
    public Result selectMyMessage(@PathVariable("start") Integer start, @PathVariable("end") Integer end,@PathVariable("master_id") Integer master_id){
        return messageService.selectMyMessage(start,end,master_id);
    }

    // 根据id查找
    @GetMapping("/findById/{id}")
    public Result selectById(@PathVariable("id") Integer id){
        return messageService.selectById(id);
    }

    // 根据姓名查找
    @GetMapping("/findByName")
    public Result selectByName(@RequestParam(name="name",  required = true) String customer_name){
        return messageService.selectByName(customer_name);
    }

    // 添加并保存
    @PostMapping("/add")
    public Result addMessage(@RequestBody Message message){
        return messageService.addMessage(message);
    }

    // 更新用户
    @PutMapping("/updateById")
    public Result updateById( @RequestBody Message message){
        return messageService.updateMessage(message);
    }

    // 删除用户
    @GetMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") int id){
        return messageService.deleteById(id);
    }

}
