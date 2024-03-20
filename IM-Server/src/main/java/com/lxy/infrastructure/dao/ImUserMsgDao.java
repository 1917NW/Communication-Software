package com.lxy.infrastructure.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxy.infrastructure.entity.ImUserMsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * (ImUserMsg)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-19 16:34:13
 */
@Mapper
public interface ImUserMsgDao extends BaseMapper<ImUserMsg> {

    List<ImUserMsg> queryFriendMsgByUserId(@Param("userId") String UserId, @Param("friendId") String friendId, @Param("talkType") Integer talkType);
    List<ImUserMsg> queryGroupMsgByTalkId(@Param("talkId") String talkId, @Param("talkType") Integer talkType);
}

