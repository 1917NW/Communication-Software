package com.lxy.infrastructure.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxy.infrastructure.entity.ImGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * (ImGroup)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-19 16:33:35
 */
@Mapper
public interface ImGroupDao extends BaseMapper<ImGroup> {


    List<ImGroup> fuzzyQueryForGroup(@Param("matchWord") String matchWord);
}

