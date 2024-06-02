package com.yy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yy.domain.entity.Tag;
import org.apache.ibatis.annotations.Param;

public interface TagMapper extends BaseMapper<Tag> {
    //使用mybatis实现逻辑删除
    int myUpdateById(@Param("id") Long id, @Param("flag") int flag);
}
