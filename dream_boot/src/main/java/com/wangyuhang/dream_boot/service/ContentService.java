package com.wangyuhang.dream_boot.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Content;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ContentService extends IService<Content> {

    Result selectAllContent(Integer start, Integer end);

    Result selectById(Integer id);

    Result selectByName(String name);

    Result addContent(Content content);

    Result updateContent(Content content);

    Result deleteById(Integer id);

    Result addLikes(Integer collecter_id,Integer content_id,Content content);

    Result deleteLikes(Integer collecter_id,Integer content_id,Content content);


    Result selectByCollecterId(Integer start, Integer collecterId);


}
