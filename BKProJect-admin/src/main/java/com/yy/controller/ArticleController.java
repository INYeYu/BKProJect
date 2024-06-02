package com.yy.controller;

import com.yy.annotation.SystemLog;
import com.yy.domain.ResponseResult;
import com.yy.domain.dto.AddArticleDto;
import com.yy.domain.dto.ArticleDto;
import com.yy.domain.entity.Article;
import com.yy.domain.vo.ArticleByIdVo;
import com.yy.domain.vo.PageVo;
import com.yy.service.ArticleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/content/article")
public class ArticleController {


    @Resource
    private ArticleService articleService;

    //写博文
    @PostMapping
    @PreAuthorize("@ps.hasPermission('content:article:writer')")//权限控制
    public ResponseResult add(@RequestBody AddArticleDto articleDto){
        return articleService.add(articleDto);
    }


    @GetMapping("/list")
    public ResponseResult list(Article article, Integer pageNum, Integer pageSize){
        PageVo pageVo = articleService.selectArticlePage(article,pageNum,pageSize);
        return ResponseResult.okResult(pageVo);
    }


    @GetMapping(value = "/{id}")
    //先查询根据文章id查询对应的文章
    public ResponseResult getInfo(@PathVariable(value = "id")Long id){
        ArticleByIdVo article = articleService.getInfo(id);
        return ResponseResult.okResult(article);
    }

    @PutMapping
    //然后才是修改文章
    public ResponseResult edit(@RequestBody ArticleDto article){
        articleService.edit(article);
        return ResponseResult.okResult();
    }


    @DeleteMapping("/{id}")
    @SystemLog(businessName = "删除文章")
    public ResponseResult delete(@PathVariable Long id){
        //直接使用mybatisplus提供的removeById方法
        articleService.removeById(id);
        return ResponseResult.okResult();
    }
}