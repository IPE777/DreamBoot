package com.wangyuhang.dream_boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Comment;
import com.wangyuhang.dream_boot.entity.Content;

public interface CommentService extends IService<Comment> {

    Result selectAllComment(Integer start, Integer end);

    Result selectById(Integer id);

    Result selectByName(String name);

    Result addComment(Comment comment);

    Result updateComment(Comment comment);

    Result deleteById(Integer id);

    Result selectByContentId(Integer contentId);
}
