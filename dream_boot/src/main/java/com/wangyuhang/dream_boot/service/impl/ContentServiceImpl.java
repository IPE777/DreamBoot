package com.wangyuhang.dream_boot.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Collect;
import com.wangyuhang.dream_boot.entity.Content;
import com.wangyuhang.dream_boot.entity.Customer;
import com.wangyuhang.dream_boot.entity.Message;
import com.wangyuhang.dream_boot.mapper.ContentMapper;
import com.wangyuhang.dream_boot.mapper.CustomerMapper;
import com.wangyuhang.dream_boot.service.CollectService;
import com.wangyuhang.dream_boot.service.ContentService;
import com.wangyuhang.dream_boot.service.MessageService;
import com.wangyuhang.dream_boot.utils.SensitivewordFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private CollectService collectService;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private MessageService messageService;

    @Override
    public Result selectAllContent(Integer start, Integer end) {
        Page<Content> page = new Page<>(start,end);
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();
        return Result.ok(contentMapper.selectPage(page,queryWrapper));
    }

    @Override
    public Result selectById(Integer id) {
        return Result.ok(contentMapper.selectById(id));
    }



    @Override
    public Result selectByName(String name) {
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_name",name);

        List<Content> list = new ArrayList<>();
        // 判断是否有输入搜索 没有则返回全部 待完善
        if (!Objects.equals(name, "")){
            list = contentMapper.selectList(queryWrapper);
        }

        return Result.ok(list);
    }

    @Override
    public Result addContent(Content content) {
        SensitivewordFilter filter = new SensitivewordFilter();
        content.setContent(filter.replaceSensitiveWord(content.getContent(),2,"*"));
        int result = contentMapper.insert(content);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("error");
        }
    }

    @Override
    public Result updateContent(Content content) {
        SensitivewordFilter filter = new SensitivewordFilter();
        content.setContent(filter.replaceSensitiveWord(content.getContent(),2,"*"));
        int result = contentMapper.updateById(content);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("err");
        }
    }

    @Override
    public Result deleteById(Integer id) {
        int result = contentMapper.deleteById(id);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("err");
        }
    }

    @Transactional
    @Override
    public Result addLikes(Integer collecter_id,Integer content_id,Content content) {
        String contentId = String.valueOf(content.getId());
        Collect collect = new Collect();
        collect.setCollecter_id(collecter_id);
        collect.setContent_id(content_id);

        Customer customer = customerMapper.selectById(collecter_id);
        Message message = new Message();
        message.setMessage("收藏了你的文章");
        message.setCustomer_id(collecter_id);
        message.setCustomer_name(customer.getName());
        message.setContent_id(content_id);
        message.setContent_photos(content.getPhotos());
        message.setMaster_id(content.getCustomer_id());

        if(collectService.isExit(collecter_id,content_id).equals(true)){
            return Result.error("已收藏过");
        }else {
            if (Boolean.FALSE.equals(redisTemplate.hasKey("contentId::"+contentId))){
                redisTemplate.opsForValue().set("contentId::"+contentId,content.getCollect());
                redisTemplate.opsForValue().increment("contentId::"+contentId);
                System.out.println("缓存不存在+1:"+redisTemplate.opsForValue().get("contentId::"+contentId));
                content.setCollect((Integer) redisTemplate.opsForValue().get("contentId::"+contentId));

            }else{
                redisTemplate.opsForValue().increment("contentId::"+contentId);
                content.setCollect((Integer) redisTemplate.opsForValue().get("contentId::"+contentId));
                System.out.println("缓存存在+1:"+redisTemplate.opsForValue().get("contentId::"+contentId));
            }
            int result = contentMapper.updateById(content);
            if (result >= 1){
                messageService.addMessage(message);
                collectService.addCollect(collect);
                return Result.ok("success");
            }else {
                return Result.error("err");
            }
        }

    }

    @Transactional
    @Override
    public Result deleteLikes(Integer collecter_id, Integer content_id, Content content) {
        String contentId = String.valueOf(content.getId());
        int collectId = collectService.select(collecter_id,content_id).getId();
        redisTemplate.opsForValue().decrement("contentId::"+contentId);
        System.out.println("缓存存在并-1:"+redisTemplate.opsForValue().get("contentId::"+contentId));
        content.setCollect((Integer) redisTemplate.opsForValue().get("contentId::"+contentId));
        int result = contentMapper.updateById(content);
        if (result >= 1){
            collectService.deleteById(collectId);
            return Result.ok("success");
        }else {
            return Result.error("err");
        }

    }

    @Override
    public Result selectByCollecterId(Integer start, Integer collecterId) {

        List<Content> list = contentMapper.get(collecterId);

        HashMap<Object, Object> records = new HashMap<>();
        records.put("list",list.get(start));
        records.put("total",list.size());


        return Result.ok(records);
    }
}
