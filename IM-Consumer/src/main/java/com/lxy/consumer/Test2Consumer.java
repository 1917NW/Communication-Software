package com.lxy.consumer;


import org.apache.dubbo.rpc.RpcContext;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "im-system",  // 主题
        consumerGroup = "private-msg-consumer2",  //消费者组
        selectorExpression = "private-msg" // 监听的tag
)
public class Test2Consumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        System.out.println("消费者组2:"+s);
//        RpcContext.getContext().set("ip", bindAddress);
    }
}
