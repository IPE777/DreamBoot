package com.wangyuhang.dream_boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Content;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ContentMapper extends BaseMapper<Content> {

    @Select("select * from customer_content a1 INNER JOIN customer_collect a2 on a1.id = a2.content_id and a2.collecter_id = #{collecterId}")
    List<Content> get(Integer collecterId);
}
