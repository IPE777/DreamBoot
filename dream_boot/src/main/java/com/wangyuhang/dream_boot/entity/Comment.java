package com.wangyuhang.dream_boot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("customer_comment")
public class Comment {

    private int id;
    private int master_id;
    private int content_id;
    private int customer_id;
    private String customer_name;
    private String customer_image;
    private String comment;
    private int likes;


    private int is_delete;
    private Date create_time;
    private Date update_time;
}
