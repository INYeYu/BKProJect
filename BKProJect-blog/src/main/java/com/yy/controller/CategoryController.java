package com.yy.controller;


import com.yy.annotation.SystemLog;
import com.yy.domain.ResponseResult;
import com.yy.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/getCategoryList")
    @SystemLog(businessName = "文章分类列表查询")
    public ResponseResult getCategoryList() {
        return categoryService.getCategoryList();
    }
}
