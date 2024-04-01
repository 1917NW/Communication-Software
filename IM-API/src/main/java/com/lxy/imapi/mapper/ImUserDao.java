package com.lxy.imapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxy.imapi.entity.ImUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImUserDao extends BaseMapper<ImUser> {

}