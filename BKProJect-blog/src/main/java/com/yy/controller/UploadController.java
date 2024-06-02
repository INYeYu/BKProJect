package com.yy.controller;


import com.yy.annotation.SystemLog;
import com.yy.domain.ResponseResult;
import com.yy.service.UploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
public class UploadController {

    @Resource
    private UploadService uploadService;

    //用户头像上传oss
    @PostMapping("/upload")
    @SystemLog(businessName = "用户头像上传")
    public ResponseResult uploadImg(MultipartFile img){
        return uploadService.uploadImg(img);
    }
}
