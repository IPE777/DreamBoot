package com.wangyuhang.dream_boot.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("biz_scheme")
public class Scheme {

    private int id;
    private String code;
    private int customer_id;
    private String customer_name;
    private String customer_address;
    private int user_id;
    private String user_name;
    private String scheme;
    private BigDecimal budget;

    private int status;
    private int is_delete;
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date create_time;
    private Date update_time;
}
