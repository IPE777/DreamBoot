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
@TableName("customer_message")
public class Message {

    private int id;
    private int master_id;
    private int customer_id;
    private String customer_name;
    private int content_id;
    private String content_photos;
    private String message;
    private int type;
    private int is_deleted;

    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date create_time;
    private Date update_time;

}
