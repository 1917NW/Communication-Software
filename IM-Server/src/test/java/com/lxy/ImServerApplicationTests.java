package com.lxy;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lxy.infrastructure.dao.ImUserDao;
import com.lxy.infrastructure.dao.ImUserMsgDao;
import com.lxy.infrastructure.dao.ImUserTalkDao;
import com.lxy.infrastructure.entity.ImUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
class ImServerApplicationTests {

    @Autowired
    private ImUserDao imUserDao;

    @Autowired
    private ImUserTalkDao imUserTalkDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
        stringRedisTemplate.opsForValue().increment("lxy");
    }

}
