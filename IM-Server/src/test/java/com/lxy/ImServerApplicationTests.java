package com.lxy;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lxy.infrastructure.dao.ImUserDao;
import com.lxy.infrastructure.entity.ImUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
class ImServerApplicationTests {

    @Autowired
    private ImUserDao imUserDao;
    @Test
    void contextLoads() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()){
            Integer next = iterator.next();
            System.out.println(next);
            iterator.remove();

        }

        System.out.println(list.size());


    }

}
