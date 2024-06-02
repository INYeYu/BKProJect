package com.yy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.constants.SystemConstants;
import com.yy.domain.ResponseResult;
import com.yy.domain.entity.Comment;
import com.yy.domain.vo.CommentVo;
import com.yy.domain.vo.PageVo;
import com.yy.enums.AppHttpCodeEnum;
import com.yy.exception.SystemException;
import com.yy.mapper.CommentMapper;
import com.yy.service.CommentService;
import com.yy.service.UserService;
import com.yy.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2024-03-31 15:21:55
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {


    @Resource
    private UserService userService;

    @Override
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        //查询对应文章的根评论
        //对articleId进行判断
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType),"article_id", articleId);

        //评论类型
        queryWrapper.eq("type",commentType);

        //根评论 rootId为-1
        queryWrapper.eq("root_id", SystemConstants.ROOT_COMMENT);

        //分页查询
        Page<Comment> page = new Page(pageNum, pageSize);
        page(page, queryWrapper);
        List<CommentVo> commentVoList = toCommentVoList(page.getRecords());

        //查询所有根评论对应的子评论集合，并且赋值对应的属性
        for (CommentVo commentVo : commentVoList) {
            //查询对应的子评论
            List<CommentVo> children = getChildren(commentVo.getId());

            //赋值
            commentVo.setChildren(children);
        }

        return ResponseResult.okResult(new PageVo(commentVoList, page.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        //        comment.setCreateBy(SecurityUtils.getUserId());

        //评论内容不能为空
        if (!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NUL);
        }
            save(comment);

        return ResponseResult.okResult();
    }

    /**
     * 根据根评论id查询对应子评论的集合
     *
     * @param id 根评论id
     * @return
     */
    private List<CommentVo> getChildren(Long id) {

        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("root_id", id);
        queryWrapper.orderByAsc("create_time");
        List<Comment> comments = list(queryWrapper);

        return toCommentVoList(comments);
    }

    private List<CommentVo> toCommentVoList(List<Comment> list) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        //遍历vo集合
        for (CommentVo commentVo : commentVos) {

            //通过createBy查询用户的昵称并赋值
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);

            //通过toCommentUserId查询用户的昵称并赋值
            //如果toCommentUserId不为-1才进行查询
            if (commentVo.getToCommentUserId() != -1) {
                String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(toCommentUserName);
            }
        }

        return commentVos;

    }
}
