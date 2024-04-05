package com.lxy.socket.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class GroupIdGenerator {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private String groupIdKey = "im_group_id";


    public  String nextId(){

        return stringRedisTemplate.opsForValue().increment(groupIdKey).toString();
    }
}
