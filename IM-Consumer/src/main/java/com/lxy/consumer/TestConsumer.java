package com.lxy.consumer;


import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "im-system",  // 主题
        consumerGroup = "private-msg-consumer",  //消费者组
        selectorExpression = "private-msg" // 监听的tag
)
public class TestConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        System.out.println("消费者组1:" +s);
    }
}
