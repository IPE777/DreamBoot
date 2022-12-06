package com.wangyuhang.dream_boot.controller;


import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Collect;
import com.wangyuhang.dream_boot.entity.Comment;
import com.wangyuhang.dream_boot.service.CollectService;
import com.wangyuhang.dream_boot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    @PostMapping("/add")
    public Result addComment(@RequestBody Collect collect){
        return collectService.addCollect(collect);
    }

    @GetMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") int id){
        return collectService.deleteById(id);
    }
}
