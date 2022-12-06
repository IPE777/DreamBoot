package com.wangyuhang.dream_boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Customer;
import com.wangyuhang.dream_boot.entity.Goods;
import com.wangyuhang.dream_boot.entity.Sensitive;
import com.wangyuhang.dream_boot.mapper.SensitiveMapper;
import com.wangyuhang.dream_boot.service.SensitiveService;
import com.wangyuhang.dream_boot.utils.AppendSentitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SensitiveServiceImpl extends ServiceImpl<SensitiveMapper, Sensitive> implements SensitiveService {

    @Autowired
    private SensitiveMapper sensitiveMapper;


    @Override
    public Result selectAllSensitive(Integer start, Integer end) {
        Page<Sensitive> page = new Page<>(start,end);
        QueryWrapper<Sensitive> queryWrapper = new QueryWrapper<>();
        return Result.ok(sensitiveMapper.selectPage(page,queryWrapper));
    }


    @Override
    public List<Sensitive> getAll() {

        return sensitiveMapper.selectList(null);
    }

    @Override
    public Result add(Sensitive sensitive) {
        int result = sensitiveMapper.insert(sensitive);
        if (result >= 1){
            AppendSentitive appendSentitive = new AppendSentitive();
            appendSentitive.addSensitive(sensitive.getName());
            return Result.ok("success");
        }else {
            return Result.error("err");
        }
    }

    @Override
    public Result selectByName(String name) {
        QueryWrapper<Sensitive> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);

        List<Sensitive> list = new ArrayList<>();
        // 判断是否有输入搜索 没有则返回全部 待完善
        if (!Objects.equals(name, "")){
            list = sensitiveMapper.selectList(queryWrapper);
        }

        return Result.ok(list);
    }


}
