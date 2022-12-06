package com.wangyuhang.dream_boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Scheme;
import com.wangyuhang.dream_boot.entity.Team;

public interface TeamService extends IService<Team> {

    Result selectAllTeam(Integer start, Integer end);

    Result selectById(Integer id);

    Result selectByName(String name);

    Result addTeam(Team team);

    Result updateTeam(Team team);

    Result deleteById(Integer id);
}
