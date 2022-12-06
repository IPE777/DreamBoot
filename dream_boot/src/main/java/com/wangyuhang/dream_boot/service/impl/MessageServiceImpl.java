package com.wangyuhang.dream_boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Customer;
import com.wangyuhang.dream_boot.entity.Message;
import com.wangyuhang.dream_boot.mapper.MessageMapper;
import com.wangyuhang.dream_boot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public Result selectAllMessage(Integer start, Integer end) {
        Page<Message> page = new Page<>(start,end);
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        return Result.ok(messageMapper.selectPage(page,queryWrapper));
    }

    @Override
    public Result selectMyMessage(Integer start, Integer end,Integer master_id) {
        Page<Message> page = new Page<>(start,end);
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("master_id",master_id).or().eq("type",1).orderByDesc("id");
        return Result.ok(messageMapper.selectPage(page,queryWrapper));
    }

    @Override
    public Result selectById(Integer id) {
        return Result.ok(messageMapper.selectById(id));
    }

    @Override
    public Result selectByName(String customer_name) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_name",customer_name);

        List<Message> list = new ArrayList<>();
        // 判断是否有输入搜索 没有则返回全部 待完善
        if (!Objects.equals(customer_name, "")){
            list = messageMapper.selectList(queryWrapper);
        }

        return Result.ok(list);
    }

    @Override
    public Result addMessage(Message message) {
        int result = messageMapper.insert(message);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("error");
        }
    }

    @Override
    public Result updateMessage(Message message) {
        int result = messageMapper.updateById(message);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("err");
        }
    }

    @Override
    public Result deleteById(Integer id) {
        int result = messageMapper.deleteById(id);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("err");
        }
    }
}
