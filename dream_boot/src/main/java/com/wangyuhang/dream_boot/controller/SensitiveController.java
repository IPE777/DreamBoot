package com.wangyuhang.dream_boot.controller;


import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Content;
import com.wangyuhang.dream_boot.entity.Sensitive;
import com.wangyuhang.dream_boot.service.SensitiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensitive")
public class SensitiveController {

    @Autowired
    private SensitiveService sensitiveService;

    @GetMapping("/getSensitive/{start}/{end}")
    public Result selectAllSensitive(@PathVariable("start") Integer start, @PathVariable("end") Integer end){
        return sensitiveService.selectAllSensitive(start,end);
    }

    @PostMapping("/add")
    public Result addSensitive(@RequestBody Sensitive sensitive){
        return sensitiveService.add(sensitive);
    }

    @GetMapping("/findByName")
    public Result selectByName(@RequestParam(name="name",  required = true) String name){
        return sensitiveService.selectByName(name);
    }

}
