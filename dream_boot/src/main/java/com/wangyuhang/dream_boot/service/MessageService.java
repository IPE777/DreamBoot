package com.wangyuhang.dream_boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Message;


public interface MessageService extends IService<Message> {

    Result selectAllMessage(Integer start, Integer end);

    Result selectMyMessage(Integer start, Integer end,Integer master_id);

    Result selectById(Integer id);

    Result selectByName(String customer_name);

    Result addMessage(Message message);

    Result updateMessage(Message message);

    Result deleteById(Integer id);
}
