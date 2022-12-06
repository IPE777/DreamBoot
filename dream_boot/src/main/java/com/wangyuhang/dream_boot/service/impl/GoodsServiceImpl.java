package com.wangyuhang.dream_boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangyuhang.dream_boot.common.Result;

import com.wangyuhang.dream_boot.entity.Goods;

import com.wangyuhang.dream_boot.mapper.GoodsMapper;
import com.wangyuhang.dream_boot.service.GoodsService;
import com.wangyuhang.dream_boot.utils.SnowFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public Result selectAllGoods(Integer start, Integer end) {
        Page<Goods> page = new Page<>(start,end);
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        return Result.ok(goodsMapper.selectPage(page,queryWrapper));
    }

    @Override
    public Result selectAll() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        return Result.ok(goodsMapper.selectList(queryWrapper));
    }

    @Override
    public Result selectById(Integer id) {
        return Result.ok(goodsMapper.selectById(id));
    }

    @Override
    public Result selectByName(String name) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);

        List<Goods> list = new ArrayList<>();
        // 判断是否有输入搜索 没有则返回全部 待完善
        if (!Objects.equals(name, "")){
            list = goodsMapper.selectList(queryWrapper);
        }

        return Result.ok(list);
    }

    @Override
    public Result addGoods(Goods goods) {
        SnowFlow snowFlow = new SnowFlow(1,1,1);
        String code = String.valueOf(snowFlow.nextId());
        goods.setCode(code);
        int result = goodsMapper.insert(goods);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("error");
        }
    }

    @Override
    public Result updateGoods(Goods goods) {
        redisTemplate.opsForValue().set("goodsCode::"+goods.getCode(),goods.getStock());
        int result = goodsMapper.updateById(goods);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("err");
        }
    }

    @Override
    public Result deleteById(Integer id) {
        int result = goodsMapper.deleteById(id);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("err");
        }
    }
}
