package com.lxy.infrastructure.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxy.infrastructure.entity.ImGroup;
import org.apache.ibatis.annotations.Mapper;


/**
 * (ImGroup)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-19 16:33:35
 */
@Mapper
public interface ImGroupDao extends BaseMapper<ImGroup> {

}

