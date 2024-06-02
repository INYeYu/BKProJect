package com.yy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yy.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-12 13:15:00
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleKeyByUserId(Long id);

    //修改用户-根据id查询用户信息
    List<Long> selectRoleIdByUserId(Long userId);
}

