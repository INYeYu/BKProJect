package com.yy.controller;


import com.yy.annotation.SystemLog;
import com.yy.domain.ResponseResult;
import com.yy.domain.entity.User;
import com.yy.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    //用户信息查询
    @GetMapping("/userInfo")
    @SystemLog(businessName = "用户信息查询")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    //用户信息更新
    @PutMapping("/userInfo")
    @SystemLog(businessName = "用户信息更新")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    //用户注册
    @PostMapping("/register")
    @SystemLog(businessName = "用户注册")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }
}
