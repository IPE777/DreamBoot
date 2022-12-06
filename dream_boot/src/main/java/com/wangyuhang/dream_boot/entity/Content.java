package com.wangyuhang.dream_boot.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("customer_content")
public class Content {

    private int id;
    private int customer_id;
    private String customer_name;
    private String customer_image;
    private String photos;
    private String title;
    private String content;
    private int likes;
    private int collect;
    private int tag;
    private int is_delete;
    private Date create_time;
    private Date update_time;
}
