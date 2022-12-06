package com.wangyuhang.dream_boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Order;
import com.wangyuhang.dream_boot.entity.Scheme;
import com.wangyuhang.dream_boot.mapper.SchemeMapper;
import com.wangyuhang.dream_boot.service.SchemeService;
import com.wangyuhang.dream_boot.utils.SnowFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SchemeServiceImpl extends ServiceImpl<SchemeMapper, Scheme> implements SchemeService {

    @Autowired
    private SchemeMapper schemeMapper;
    @Override
    public Result selectAllScheme(Integer start, Integer end) {
        Page<Scheme> page = new Page<>(start,end);
        QueryWrapper<Scheme> queryWrapper = new QueryWrapper<>();
        return Result.ok(schemeMapper.selectPage(page,queryWrapper));
    }

    @Override
    public Result selectById(Integer id) {
        return Result.ok(schemeMapper.selectById(id));
    }

    @Override
    public Result selectByName(String name) {
        QueryWrapper<Scheme> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_name",name);

        List<Scheme> list = new ArrayList<>();
        // 判断是否有输入搜索 没有则返回全部 待完善
        if (!Objects.equals(name, "")){
            list = schemeMapper.selectList(queryWrapper);
        }

        return Result.ok(list);
    }


    @Override
    public Result addScheme(Scheme scheme) {
        SnowFlow snowFlow = new SnowFlow(1,1,1);
        String code = String.valueOf(snowFlow.nextId());
        scheme.setCode(code);
        int result = schemeMapper.insert(scheme);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("error");
        }
    }

    @Override
    public Result updateScheme(Scheme scheme) {
        int result = schemeMapper.updateById(scheme);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("err");
        }
    }

    @Override
    public Result deleteById(Integer id) {
        int result = schemeMapper.deleteById(id);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("err");
        }
    }
}
