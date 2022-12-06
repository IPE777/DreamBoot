package com.wangyuhang.dream_boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Order;
import com.wangyuhang.dream_boot.entity.Scheme;

public interface SchemeService extends IService<Scheme> {

    Result selectAllScheme(Integer start, Integer end);

    Result selectById(Integer id);

    Result selectByName(String name);

    Result addScheme(Scheme scheme);

    Result updateScheme(Scheme scheme);

    Result deleteById(Integer id);
}
