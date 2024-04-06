package com.lxy.infrastructure.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxy.infrastructure.entity.ImOfflineMsg;
import org.apache.ibatis.annotations.Mapper;

/**
 * (ImOfflineMsg)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-02 16:11:38
 */
@Mapper
public interface ImOfflineMsgDao extends BaseMapper<ImOfflineMsg> {

}

