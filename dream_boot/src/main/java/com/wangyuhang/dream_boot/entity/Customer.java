package com.wangyuhang.dream_boot.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("customer_user")
public class Customer {

    private int id;
    private String name;
    private String image;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String address;
    private int sex;
    private String role;
    private String salt;
    private int is_delete;
    private Date create_time;
    private Date update_time;
}
