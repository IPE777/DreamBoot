package com.wangyuhang.dream_boot.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Customer;
import com.wangyuhang.dream_boot.entity.Goods;
import com.wangyuhang.dream_boot.entity.GoodsOrder;
import com.wangyuhang.dream_boot.mapper.GoodsMapper;
import com.wangyuhang.dream_boot.mapper.GoodsOrderMapper;
import com.wangyuhang.dream_boot.service.GoodsOrderService;
import com.wangyuhang.dream_boot.utils.SnowFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GoodsOrderServiceImpl extends ServiceImpl<GoodsOrderMapper, GoodsOrder> implements GoodsOrderService {

    @Autowired
    private GoodsOrderMapper goodsOrderMapper;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public Result selectAllGoodsOrder(Integer start, Integer end) {
        Page<GoodsOrder> page = new Page<>(start,end);
        QueryWrapper<GoodsOrder> queryWrapper = new QueryWrapper<>();
        return Result.ok(goodsOrderMapper.selectPage(page,queryWrapper));
    }

    @Override
    public Result selectMyGoodsOrder(Integer start, Integer end,Integer customer_id) {
        Page<GoodsOrder> page = new Page<>(start,end);
        QueryWrapper<GoodsOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_id",customer_id);
        return Result.ok(goodsOrderMapper.selectPage(page,queryWrapper));
    }

    @Override
    public Result selectById(Integer id) {
        return Result.ok(goodsOrderMapper.selectById(id));
    }

    @Override
    public Result selectByName(String name) {
        QueryWrapper<GoodsOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_name",name);

        List<GoodsOrder> list = new ArrayList<>();
        // 判断是否有输入搜索 没有则返回全部 待完善
        if (!Objects.equals(name, "")){
            list = goodsOrderMapper.selectList(queryWrapper);
        }

        return Result.ok(list);
    }

    @Transactional
    @Override
    public Result addGoodsOrder(GoodsOrder goodsOrder) {
        SnowFlow snowFlow = new SnowFlow(1,1,1);
        String code = String.valueOf(snowFlow.nextId());
        goodsOrder.setCode(code);

        Goods goods = goodsMapper.selectById(goodsOrder.getGoods_id());
        String goodsCode = goods.getCode();

        if (Boolean.FALSE.equals(redisTemplate.hasKey("goodsCode::"+goodsCode))){
            redisTemplate.opsForValue().set("goodsCode::"+ goodsCode,goods.getStock());
            if ((int) redisTemplate.opsForValue().get("goodsCode::"+ goodsCode) - goodsOrder.getCount() < 0){
                return Result.error("库存不足");
            }else{
                redisTemplate.opsForValue().decrement("goodsCode::"+goodsCode,goodsOrder.getCount());
            }
            System.out.println("缓存不存在-减库存还剩：:"+redisTemplate.opsForValue().get("goodsCode::"+goodsCode));
            goods.setStock((Integer) redisTemplate.opsForValue().get("goodsCode::"+goodsCode));

            int result = goodsOrderMapper.insert(goodsOrder);
            if (result >= 1){
                goodsMapper.updateById(goods);
                return Result.ok("success");
            }else {
                return Result.error("error");
            }

        }else{
            if ((int) redisTemplate.opsForValue().get("goodsCode::"+ goodsCode) - goodsOrder.getCount() < 0){
                return Result.error("库存不足");
            }else{
                redisTemplate.opsForValue().decrement("goodsCode::"+goodsCode,goodsOrder.getCount());
            }
            System.out.println("缓存存在-减库存还剩:"+redisTemplate.opsForValue().get("goodsCode::"+goodsCode));
            goods.setStock((Integer) redisTemplate.opsForValue().get("goodsCode::"+goodsCode));

            int result = goodsOrderMapper.insert(goodsOrder);
            if (result >= 1){
                goodsMapper.updateById(goods);
                return Result.ok("success");
            }else {
                return Result.error("error");
            }
        }

    }

    @Override
    public Result updateGoodsOrder(GoodsOrder goodsOrder) {
        int result = goodsOrderMapper.updateById(goodsOrder);

        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("err");
        }
    }

    @Override
    public Result deleteById(Integer id) {
        int result = goodsOrderMapper.deleteById(id);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("err");
        }
    }
}
