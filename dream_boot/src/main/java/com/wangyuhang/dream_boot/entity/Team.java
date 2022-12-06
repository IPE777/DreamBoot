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
@TableName("biz_team")
public class Team {

    private int id;
    private String code;
    private String name;
    private String contact;
    private String phone;
    private String address;
    private String license;
    private int satisfied;
    private int order_sum;
    private int is_delete;
    private Date create_time;
    private Date update_time;
}
