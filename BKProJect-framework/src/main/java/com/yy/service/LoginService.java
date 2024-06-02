package com.yy.service;

import com.yy.domain.ResponseResult;
import com.yy.domain.entity.User;

public interface LoginService {
    public ResponseResult login(User user);

    ResponseResult logout();
}
