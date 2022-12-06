package com.wangyuhang.dream_boot.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("biz_goods")
public class Goods {

    private int id;
    private String code;
    private String name;
    private String photos;
    private BigDecimal price;
    private int stock;
    private int type;
    private int is_delete;
    private Date create_time;
    private Date update_time;
}
