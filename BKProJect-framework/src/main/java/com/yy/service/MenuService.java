package com.yy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.domain.entity.Menu;

import java.util.List;


/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2024-04-12 13:07:00
 */
public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    //查询菜单列表
    List<Menu> selectMenuList(Menu menu);

    //删除菜单-判断是否存在子菜单
    boolean hasChild(Long menuId);

    //修改角色-根据角色id查询对应角色菜单列表树
    List<Long> selectMenuListByRoleId(Long roleId);
}

