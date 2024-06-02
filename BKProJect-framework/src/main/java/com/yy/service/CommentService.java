package com.yy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.domain.ResponseResult;
import com.yy.domain.entity.Comment;

/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2024-03-31 15:21:55
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}

