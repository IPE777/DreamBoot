package com.wangyuhang.dream_boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Scheme;
import com.wangyuhang.dream_boot.entity.Team;
import com.wangyuhang.dream_boot.mapper.TeamMapper;
import com.wangyuhang.dream_boot.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team> implements TeamService {

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public Result selectAllTeam(Integer start, Integer end) {
        Page<Team> page = new Page<>(start,end);
        QueryWrapper<Team> queryWrapper = new QueryWrapper<>();
        return Result.ok(teamMapper.selectPage(page,queryWrapper));
    }

    @Override
    public Result selectById(Integer id) {
        return Result.ok(teamMapper.selectById(id));
    }

    @Override
    public Result selectByName(String name) {
        QueryWrapper<Team> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);

        List<Team> list = new ArrayList<>();
        // 判断是否有输入搜索 没有则返回全部 待完善
        if (!Objects.equals(name, "")){
            list = teamMapper.selectList(queryWrapper);
        }

        return Result.ok(list);
    }

    @Override
    public Result addTeam(Team team) {
        String code = "自动生成待完成";
        team.setCode(code);
        int result = teamMapper.insert(team);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("error");
        }
    }

    @Override
    public Result updateTeam(Team team) {
        int result = teamMapper.updateById(team);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("err");
        }
    }

    @Override
    public Result deleteById(Integer id) {
        int result = teamMapper.deleteById(id);
        if (result >= 1){
            return Result.ok("success");
        }else {
            return Result.error("err");
        }
    }
}
