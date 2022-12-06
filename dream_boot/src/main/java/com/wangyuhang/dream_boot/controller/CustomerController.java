package com.wangyuhang.dream_boot.controller;


import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Customer;
import com.wangyuhang.dream_boot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/getCustomer/{start}/{end}")
    public Result selectAllUser(@PathVariable("start") Integer start, @PathVariable("end") Integer end){
        return customerService.selectAllCustomer(start,end);
    }

    // 根据id查找
    @GetMapping("/findById/{id}")
    public Result selectById(@PathVariable("id") Integer id){
        return customerService.selectById(id);
    }

    // 根据姓名查找
    @GetMapping("/findByName")
    public Result selectByName(@RequestParam(name="name",  required = true) String name){
        return customerService.selectByName(name);
    }

    // 添加用户并保存
    @PostMapping("/add")
    public Result addUser(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }

    // 更新用户
    @PutMapping("/updateById")
    public Result updateById( @RequestBody Customer customer){
        return customerService.updateCustomer(customer);
    }

    // 删除用户
    @GetMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") int id){
        return customerService.deleteById(id);
    }

    @GetMapping("/getContent/{start}/{end}")
    public Result selectMyContent(@PathVariable("start") Integer start, @PathVariable("end") Integer end,@RequestParam(name="customerId",  required = true) Integer customerId){
        return customerService.selectMyContent(start,end,customerId);
    }

}
