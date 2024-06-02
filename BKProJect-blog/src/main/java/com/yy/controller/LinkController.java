package com.yy.controller;

import com.yy.annotation.SystemLog;
import com.yy.domain.ResponseResult;
import com.yy.service.LinkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Resource
    private LinkService linkService;

    //查询友链
    @GetMapping("/getAllLink")
    @SystemLog(businessName = "友链查询")
    public ResponseResult getAllLink() {
        return linkService.getAllLink();
    }
}
