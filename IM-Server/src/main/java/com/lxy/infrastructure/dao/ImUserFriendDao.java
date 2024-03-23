package com.lxy.infrastructure.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxy.infrastructure.entity.ImUserFriend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * (ImUserFriend)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-19 16:33:49
 */
@Mapper
public interface ImUserFriendDao extends BaseMapper<ImUserFriend> {

    List<String> queryAllFriendByUserId(@Param("userId") String userId);
}

