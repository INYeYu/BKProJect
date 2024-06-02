package com.yy.controller;

import com.yy.annotation.SystemLog;
import com.yy.domain.ResponseResult;
import com.yy.domain.dto.AddTagDto;
import com.yy.domain.dto.EditTagDto;
import com.yy.domain.dto.TagListDto;
import com.yy.domain.entity.Tag;
import com.yy.domain.vo.TagVo;
import com.yy.service.TagService;
import com.yy.utils.BeanCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Resource
    private TagService tagService;

    @GetMapping("/list")
    @SystemLog(businessName = "标签列表")
    public ResponseResult list(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        return tagService.pageTagList(pageNum, pageSize, tagListDto);
    }

    //标签增加
    @PostMapping
    @SystemLog(businessName = "标签增加")
    public ResponseResult tagAdd(@RequestBody AddTagDto tagDto) {
        Tag tag= BeanCopyUtils.copyBean(tagDto, Tag.class);
        tagService.save(tag);
        return ResponseResult.okResult();
    }
    //删除标签
    //bug:对于多标签删除会报错待修
    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        tagService.removeById(id);
        return ResponseResult.okResult();
    }

    @GetMapping("/{id}")
    //根据标签的id来查询标签
    public ResponseResult getInfo(@PathVariable(value = "id")Long id){
        Tag tag = tagService.getById(id);
        return ResponseResult.okResult(tag);
    }

    @PutMapping
    // 根据标签的id来修改标签
    public ResponseResult edit(@RequestBody EditTagDto tagDto){
        Tag tag = BeanCopyUtils.copyBean(tagDto,Tag.class);
        tagService.updateById(tag);
        return ResponseResult.okResult();
    }

    //写文章——查询文章
    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        List<TagVo> list = tagService.listAllTag();
        return ResponseResult.okResult(list);
    }
}