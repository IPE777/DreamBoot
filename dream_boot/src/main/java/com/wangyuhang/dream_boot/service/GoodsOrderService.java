package com.wangyuhang.dream_boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Customer;
import com.wangyuhang.dream_boot.entity.GoodsOrder;
import com.wangyuhang.dream_boot.mapper.GoodsMapper;
import com.wangyuhang.dream_boot.mapper.GoodsOrderMapper;

public interface GoodsOrderService extends IService<GoodsOrder> {

    Result selectAllGoodsOrder(Integer start, Integer end);

    Result selectMyGoodsOrder(Integer start, Integer end,Integer customer_id);

    Result selectById(Integer id);

    Result selectByName(String name);

    Result addGoodsOrder(GoodsOrder goodsOrder);

    Result updateGoodsOrder(GoodsOrder goodsOrder);

    Result deleteById(Integer id);
}
