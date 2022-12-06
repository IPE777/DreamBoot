package com.wangyuhang.dream_boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Collect;
import com.wangyuhang.dream_boot.entity.Comment;
import com.wangyuhang.dream_boot.mapper.CollectMapper;
import com.wangyuhang.dream_boot.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {

    @Autowired
    private CollectMapper collectMapper;

    @Override
    public Result selectAllCollect(Integer start, Integer end) {
        Page<Collect> page = new Page<>(start,end);
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        return Result.ok(collectMapper.selectPage(page,queryWrapper));
    }

    @Override
    public Result selectById(Integer id) {
        return Result.ok(collectMapper.selectById(id));
    }

//    @Override
//    public Result selectByName(String name) {
//        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("customer_name",name);
//
//        List<Comment> list = new ArrayList<>();
//        // 判断是否有输入搜索 没有则返回全部 待完善
//        if (!Objects.equals(name, "")){
//            list = collectMapper.selectList(queryWrapper);
//        }
//
//        return Result.ok(list);
//    }

    @Override
    public Result addCollect(Collect collect) {
        int result = collectMapper.insert(collect);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("error");
        }
    }

    @Override
    public Result updateCollect(Collect collect) {
        int result = collectMapper.updateById(collect);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("err");
        }
    }

    @Override
    public Result deleteById(Integer id) {
        int result = collectMapper.deleteById(id);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("err");
        }
    }


    @Override
    public Collect select(Integer collecterId,Integer contentId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("collecter_id",collecterId).eq("content_id",contentId);
        return collectMapper.selectOne(queryWrapper);
    }

    @Override
    public Boolean isExit(Integer collecter_id,Integer content_id) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("collecter_id",collecter_id).eq("content_id",content_id);
        Collect collect = collectMapper.selectOne(queryWrapper);
        return null != collect;
    }
}
