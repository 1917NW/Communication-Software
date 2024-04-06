package com.lxy;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootTest
class TestNacosApplicationTests {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    void contextLoads() {
        String msg = "hello";
        rocketMQTemplate.send("im-system" + ":" + "private-msg",
                MessageBuilder.withPayload(msg).build());
    }

}
