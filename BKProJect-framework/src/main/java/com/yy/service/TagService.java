package com.yy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.domain.ResponseResult;
import com.yy.domain.dto.TagListDto;
import com.yy.domain.entity.Tag;
import com.yy.domain.vo.PageVo;
import com.yy.domain.vo.TagVo;

import java.util.List;

public interface TagService extends IService<Tag> {
    //查询标签列表
    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    List<TagVo> listAllTag();
}