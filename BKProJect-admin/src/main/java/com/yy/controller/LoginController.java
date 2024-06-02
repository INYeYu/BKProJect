package com.yy.controller;

import com.yy.domain.ResponseResult;
import com.yy.domain.entity.LoginUser;
import com.yy.domain.entity.Menu;
import com.yy.domain.entity.User;
import com.yy.domain.vo.AdminUserInfoVo;
import com.yy.domain.vo.RoutersVo;
import com.yy.domain.vo.UserInfoVo;
import com.yy.enums.AppHttpCodeEnum;
import com.yy.exception.SystemException;
import com.yy.service.LoginService;
import com.yy.service.MenuService;
import com.yy.service.RoleService;
import com.yy.utils.BeanCopyUtils;
import com.yy.utils.SecurityUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class LoginController {
    @Resource
    private LoginService loginService;

    @Resource
    private MenuService menuService;

    @Resource
    private RoleService roleService;


    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUserName())) {
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }


    @GetMapping("getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo() {
        //获取当前登录的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据用户id查询用户信息
        List<String> rolesKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());
        //封装数据返回
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms, rolesKeyList, userInfoVo);

        return ResponseResult.okResult(adminUserInfoVo);
    }

    @GetMapping("getRouters")
    public ResponseResult<RoutersVo> getRouters() {
        Long userId = SecurityUtils.getUserId();
        //查询menu 结果是tree的形式
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);
        //封装数据返回
        return ResponseResult.okResult(new RoutersVo(menus));
    }

    @RequestMapping("/user/logout")
    public ResponseResult logout() {
        return loginService.logout();
    }

}