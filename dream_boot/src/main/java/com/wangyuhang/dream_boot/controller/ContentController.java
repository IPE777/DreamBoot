package com.wangyuhang.dream_boot.controller;


import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Content;

import com.wangyuhang.dream_boot.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;


    @GetMapping("/getContent/{start}/{end}")
    public Result selectAllContent(@PathVariable("start") Integer start, @PathVariable("end") Integer end){
        return contentService.selectAllContent(start,end);
    }

    // 根据id查找
    @GetMapping("/findById/{id}")
    public Result selectById(@PathVariable("id") Integer id){
        return contentService.selectById(id);
    }


    @GetMapping("/findByName")
    public Result selectByName(@RequestParam(name="name",  required = true) String name){
        return contentService.selectByName(name);
    }


    @PostMapping("/add")
    public Result addContent(@RequestBody Content content){
        return contentService.addContent(content);
    }


    @PutMapping("/updateById")
    public Result updateById( @RequestBody Content content){
        return contentService.updateContent(content);
    }


    @GetMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") int id){
        return contentService.deleteById(id);
    }

    @PostMapping("/addLikes/{collecter_id}/{content_id}")
    public Result addLikes(@PathVariable("collecter_id") Integer collecter_id, @PathVariable("content_id") Integer content_id,@RequestBody Content content){
        return contentService.addLikes(collecter_id,content_id,content);
    }

    @PostMapping("/deleteLikes/{collecter_id}/{content_id}")
    public Result deleteLikes(@PathVariable("collecter_id") Integer collecter_id, @PathVariable("content_id") Integer content_id,@RequestBody Content content){
        return contentService.deleteLikes(collecter_id,content_id,content);
    }

    @GetMapping("/findByCollecterId/{start}")
    public Result selectByName(@PathVariable("start") Integer start,@RequestParam(name="collecterId",  required = true) Integer collecterId){
        return contentService.selectByCollecterId(start,collecterId);
    }

}
