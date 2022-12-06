package com.wangyuhang.dream_boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Collect;
import com.wangyuhang.dream_boot.entity.Comment;
import org.apache.ibatis.annotations.Select;

public interface CollectService extends IService<Collect> {

    Result selectAllCollect(Integer start, Integer end);

    Result selectById(Integer id);

//    Result selectByName(String name);

    Result addCollect(Collect collect);

    Result updateCollect(Collect collect);

    Result deleteById(Integer id);

    Collect select(Integer collecterId,Integer contentId);

//    Result selectByCollecterId(Integer collecterId);

    Boolean isExit(Integer collecter_id,Integer content_id);


}
