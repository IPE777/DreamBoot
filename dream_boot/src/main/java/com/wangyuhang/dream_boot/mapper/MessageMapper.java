package com.wangyuhang.dream_boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangyuhang.dream_boot.entity.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}
