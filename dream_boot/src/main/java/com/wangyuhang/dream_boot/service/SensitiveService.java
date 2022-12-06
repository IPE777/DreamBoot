package com.wangyuhang.dream_boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Sensitive;

import java.util.List;


public interface SensitiveService extends IService<Sensitive> {

    Result selectAllSensitive(Integer start, Integer end);
    List<Sensitive> getAll();

    Result add(Sensitive sensitive);

    Result selectByName(String name);
}
