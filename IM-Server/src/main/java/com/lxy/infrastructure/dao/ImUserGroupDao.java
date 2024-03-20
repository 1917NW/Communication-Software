package com.lxy.infrastructure.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxy.infrastructure.entity.ImUserGroup;
import org.apache.ibatis.annotations.Mapper;


/**
 * (ImUserGroup)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-19 16:34:01
 */
@Mapper
public interface ImUserGroupDao extends BaseMapper<ImUserGroup> {

}

