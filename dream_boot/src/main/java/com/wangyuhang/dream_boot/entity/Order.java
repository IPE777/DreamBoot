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
@TableName("biz_order")
public class Order {

    private int id;
    private String code;
    private int customer_id;
    private String customer_name;
    private String customer_phone;
    private String customer_address;
    private String photos;
    private int status;
    private int is_delete;
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date create_time;
    private Date update_time;
}
