package com.lxy;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lxy.infrastructure.dao.ImUserDao;
import com.lxy.infrastructure.entity.ImUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class ImServerApplicationTests {

    @Autowired
    private ImUserDao imUserDao;
    @Test
    void contextLoads() {
        String userId = "123";
        LambdaQueryWrapper<ImUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ImUser::getUserId, userId);
        lambdaQueryWrapper.select(ImUser::getUserPassword);
        ImUser imUser = imUserDao.selectOne(lambdaQueryWrapper);
        System.out.println(imUser);

    }

}
