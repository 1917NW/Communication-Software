package com.lxy.infrastructure.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxy.infrastructure.entity.ImUser;
import org.apache.ibatis.annotations.Mapper;


/**
 * (ImUser)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-19 16:29:44
 */
@Mapper
public interface ImUserDao extends BaseMapper<ImUser> {

}

