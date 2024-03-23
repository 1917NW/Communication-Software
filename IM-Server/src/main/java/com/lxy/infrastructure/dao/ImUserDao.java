package com.lxy.infrastructure.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxy.infrastructure.entity.ImUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * (ImUser)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-19 16:29:44
 */
@Mapper
public interface ImUserDao extends BaseMapper<ImUser> {

    /**
     * 带有排除结果的模糊查询
     * @param excludeUserId 结果中排除userId为excludeUserId的记录
     * @param matchWord 匹配词，匹配字段为userId或者userNickname
     * @return
     */
    List<ImUser> fuzzySearchExcludeUserId(@Param("excludeUserId")String excludeUserId, @Param("matchWord") String matchWord);
}

