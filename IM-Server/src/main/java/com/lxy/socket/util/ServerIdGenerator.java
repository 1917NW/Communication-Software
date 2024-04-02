package com.lxy.socket.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class ServerIdGenerator {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private String serverIdKey = "im_server_id";
    public  String nextId(){

        return stringRedisTemplate.opsForValue().increment(serverIdKey).toString();
    }
}
