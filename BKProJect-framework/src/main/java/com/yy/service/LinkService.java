package com.yy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.domain.ResponseResult;
import com.yy.domain.entity.Link;
import com.yy.domain.vo.PageVo;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2024-03-20 22:00:19
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();

    //分页查询友链
    PageVo selectLinkPage(Link link, Integer pageNum, Integer pageSize);
}

