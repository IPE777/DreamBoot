package com.wangyuhang.dream_boot.entity;


import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User {

    private int id;
    private String name;
    private String image;
    private String username;
    private String password;
    private String phone;
    private String email;

    @JsonFormat(pattern ="yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date birthday;
    private int sex;
    private String role;
    private String salt;
    private int is_delete;
    private String sys_code;
    private Date create_time;
    private Date update_time;
}
