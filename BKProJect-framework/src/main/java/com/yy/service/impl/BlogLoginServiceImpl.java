package com.yy.service.impl;

import com.yy.domain.ResponseResult;
import com.yy.domain.entity.LoginUser;
import com.yy.domain.entity.User;
import com.yy.domain.vo.BlogUserLoginVo;
import com.yy.domain.vo.UserInfoVo;
import com.yy.service.BlogLoginService;
import com.yy.utils.BeanCopyUtils;
import com.yy.utils.JwtUtil;
import com.yy.utils.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Resource
    private RedisCache redisCache;

    @Resource
    private AuthenticationManager authenticationManager;


    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或者密码错误");
        }
        //获取userid 生存token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //用户信息存入redis
        redisCache.setCacheObject("bloglogin:" + userId, loginUser);
        //token和userinfo封装 返回
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        BlogUserLoginVo vo = new BlogUserLoginVo(jwt, userInfoVo);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult logout() {

        //读取token 解析获得Userid
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal());
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //获取Userid
        Long userId = loginUser.getUser().getId();
        //删除redis中的用户信息
        redisCache.deleteObject("bloglogin:" + userId);
        return ResponseResult.okResult();
    }
}
