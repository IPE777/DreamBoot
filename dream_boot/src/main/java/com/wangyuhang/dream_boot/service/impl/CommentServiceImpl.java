package com.wangyuhang.dream_boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Comment;
import com.wangyuhang.dream_boot.entity.Content;
import com.wangyuhang.dream_boot.entity.Message;
import com.wangyuhang.dream_boot.mapper.CommentMapper;
import com.wangyuhang.dream_boot.mapper.ContentMapper;
import com.wangyuhang.dream_boot.service.CommentService;
import com.wangyuhang.dream_boot.service.MessageService;
import com.wangyuhang.dream_boot.utils.SensitivewordFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public Result selectAllComment(Integer start, Integer end) {
        Page<Comment> page = new Page<>(start,end);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        return Result.ok(commentMapper.selectPage(page,queryWrapper));
    }

    @Override
    public Result selectById(Integer id) {
        return Result.ok(commentMapper.selectById(id));
    }

    @Override
    public Result selectByName(String name) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_name",name);

        List<Comment> list = new ArrayList<>();
        // 判断是否有输入搜索 没有则返回全部 待完善
        if (!Objects.equals(name, "")){
            list = commentMapper.selectList(queryWrapper);
        }

        return Result.ok(list);
    }

    @Transactional
    @Override
    public Result addComment(Comment comment) {
        Content content = contentMapper.selectById(comment.getContent_id());
        SensitivewordFilter filter = new SensitivewordFilter();
        comment.setComment(filter.replaceSensitiveWord(comment.getComment(),2,"*"));
        Message message = new Message();
        message.setMessage("评论了你的文章："+comment.getComment());
        message.setCustomer_id(comment.getCustomer_id());
        message.setCustomer_name(comment.getCustomer_name());
        message.setContent_id(comment.getContent_id());
        message.setContent_photos(content.getPhotos());

        message.setMaster_id(content.getCustomer_id());

        int result = commentMapper.insert(comment);
        if (result >= 1){
            messageService.addMessage(message);
            return Result.ok("success");
        }else {
            return Result.error("error");
        }
    }

    @Override
    public Result updateComment(Comment comment) {
        int result = commentMapper.updateById(comment);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("err");
        }
    }

    @Override
    public Result deleteById(Integer id) {
        int result = commentMapper.deleteById(id);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("err");
        }
    }

    @Override
    public Result selectByContentId(Integer contentId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("content_id",contentId);

        List<Comment> list = new ArrayList<>();
        // 判断是否有输入搜索 没有则返回全部 待完善
        if (!Objects.equals(contentId, "")){
            list = commentMapper.selectList(queryWrapper);
        }

        return Result.ok(list);
    }
}
