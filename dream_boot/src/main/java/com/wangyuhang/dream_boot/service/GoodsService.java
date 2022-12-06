package com.wangyuhang.dream_boot.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wangyuhang.dream_boot.common.Result;

import com.wangyuhang.dream_boot.entity.Goods;


public interface GoodsService extends IService<Goods> {
    Result selectAllGoods(Integer start, Integer end);
    Result selectAll();

    Result selectById(Integer id);

    Result selectByName(String name);

    Result addGoods(Goods goods);

    Result updateGoods(Goods goods);

    Result deleteById(Integer id);
}
