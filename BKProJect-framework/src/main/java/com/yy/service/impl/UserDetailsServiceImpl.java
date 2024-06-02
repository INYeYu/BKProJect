package com.yy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yy.constants.SystemConstants;
import com.yy.domain.entity.LoginUser;
import com.yy.domain.entity.User;
import com.yy.mapper.MenuMapper;
import com.yy.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;


    @Resource
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
//        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
//        queryWrapper.eq(User::getUserName,username);


        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_name",username);
        User user = userMapper.selectOne(queryWrapper);
        //判断是否查到用户 没查到抛出异常
        if (Objects.isNull(user)){
            throw new RuntimeException("用户不存在");
        }
        if(user.getType().equals(SystemConstants.IS_ADMAIN)){
            //根据用户id查询权限关键字，即list是权限信息的集合
            List<String> list = menuMapper.selectPermsByUserId(user.getId());
            return new LoginUser(user,list);
        }
        //返回用户信息
        //TODO 查询权限信息封装
        return new LoginUser(user,null);
    }
}
