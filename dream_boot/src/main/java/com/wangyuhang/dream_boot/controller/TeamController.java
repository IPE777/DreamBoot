package com.wangyuhang.dream_boot.controller;

import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Scheme;
import com.wangyuhang.dream_boot.entity.Team;
import com.wangyuhang.dream_boot.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;


    @GetMapping("/getTeam/{start}/{end}")
    public Result selectAllTeam(@PathVariable("start") Integer start, @PathVariable("end") Integer end){
        return teamService.selectAllTeam(start,end);
    }

    // 根据id查找
    @GetMapping("/findById/{id}")
    public Result selectById(@PathVariable("id") Integer id){
        return teamService.selectById(id);
    }

    // 根据姓名查找
    @GetMapping("/findByName")
    public Result selectByName(@RequestParam(name="name",  required = true) String name){
        return teamService.selectByName(name);
    }

    // 添加用户并保存
    @PostMapping("/add")
    public Result addTeam(@RequestBody Team team){
        return teamService.addTeam(team);
    }

    // 更新用户
    @PutMapping("/updateById")
    public Result updateById( @RequestBody Team team){
        return teamService.updateTeam(team);
    }

    // 删除用户
    @GetMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") int id){
        return teamService.deleteById(id);
    }

}
