package com.wangyuhang.dream_boot.controller;

import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Comment;
import com.wangyuhang.dream_boot.entity.Content;
import com.wangyuhang.dream_boot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/getComment/{start}/{end}")
    public Result selectAllComment(@PathVariable("start") Integer start, @PathVariable("end") Integer end){
        return commentService.selectAllComment(start,end);
    }

    // 根据id查找
    @GetMapping("/findById/{id}")
    public Result selectById(@PathVariable("id") Integer id){
        return commentService.selectById(id);
    }

    // 根据姓名查找
    @GetMapping("/findByName")
    public Result selectByName(@RequestParam(name="name",  required = true) String name){
        return commentService.selectByName(name);
    }

    // 添加用户并保存
    @PostMapping("/add")
    public Result addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }

    // 更新用户
    @PutMapping("/updateById")
    public Result updateById( @RequestBody Comment comment){
        return commentService.updateComment(comment);
    }

    // 删除用户
    @GetMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") int id){
        return commentService.deleteById(id);
    }

    @GetMapping("/findByContentId")
    public Result selectByContentId(@RequestParam(name="contentId",  required = true) Integer contentId){
        return commentService.selectByContentId(contentId);
    }

}
