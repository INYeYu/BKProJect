package com.yy.controller;

import com.yy.annotation.SystemLog;
import com.yy.constants.SystemConstants;
import com.yy.domain.ResponseResult;
import com.yy.domain.entity.Comment;
import com.yy.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/comment")
@Api(tags = "评论",description = "评论相关接口")
public class CommentController {

    @Resource
    private CommentService commentService;

    @GetMapping("/commentList")
    @SystemLog(businessName = "热门文章评论查询")
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT,articleId,pageNum,pageSize);
    }

    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment){
       return commentService.addComment(comment);
    }


    @GetMapping("/linkCommentList")
    @SystemLog(businessName = "友链评论查询")
    @ApiOperation(value = "友链评论列表",notes = "获取一页友链评论")
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemConstants.LINK_COMMENT,null,pageNum,pageSize);
    }
}
