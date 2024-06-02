package com.yy.controller;

import com.yy.annotation.SystemLog;
import com.yy.domain.ResponseResult;
import com.yy.domain.entity.User;
import com.yy.enums.AppHttpCodeEnum;
import com.yy.exception.SystemException;
import com.yy.service.BlogLoginService;
import org.apache.poi.util.StringUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BlogLoginController {
    @Resource
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    @SystemLog(businessName = "登录")
    public ResponseResult login(@RequestBody User user) {
        if(!StringUtils.hasText(user.getUserName())){
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    @SystemLog(businessName = "登出")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }
}
