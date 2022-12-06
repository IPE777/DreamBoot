package com.wangyuhang.dream_boot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("customer_collect")
public class Collect {

    private int id;
    private int collecter_id;
    private int content_id;
}
