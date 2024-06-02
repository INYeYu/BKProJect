package com.yy.service;

import com.yy.domain.ResponseResult;
import com.yy.domain.entity.User;


public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
