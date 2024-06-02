package com.yy.controller;


import com.yy.annotation.SystemLog;
import com.yy.domain.ResponseResult;
import com.yy.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

//    @GetMapping("/list")
//    public List<Article> test(){
//        return articleService.list();
//    }


    @GetMapping("/hotArticleList")
    @SystemLog(businessName = "热门文章列表查询")
    public ResponseResult hotArticleList() {
        //查询热门文章，封装成ResponseResult返回
        ResponseResult result = articleService.hotArticleList();
        return result;

    }

    @GetMapping("/articleList")
    @SystemLog(businessName = "文章列表查询")
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        return articleService.articleList(pageNum, pageSize, categoryId);
    }

    @GetMapping("/{id}")
    @SystemLog(businessName = "文章查询")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id) {
        return articleService.getArticleDetail(id);
    }


    @PutMapping("/updateViewCount/{id}")
    @SystemLog(businessName = "浏览量查询")
    public ResponseResult updateViewCount(@PathVariable("id") Long id){
        return articleService.updateViewCount(id);

    }
}
