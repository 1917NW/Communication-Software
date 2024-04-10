package com.lxy.socket.handler.impl.friend;


import com.lxy.protocolpackage.mq.MessageTopic;
import com.lxy.protocolpackage.protocol.friend.AddFriendResponse;
import com.lxy.protocolpackage.protocol.friend.DeleteFriendRequest;
import com.lxy.socket.handler.AbstractBizHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class DeleteFriendRequestHandler extends AbstractBizHandler<DeleteFriendRequest> {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void channelRead(Channel channel, DeleteFriendRequest msg) {
        rocketMQTemplate.send(MessageTopic.getDeleteFriendRequestTag(), MessageBuilder.withPayload(msg).build());
        return;
    }
}
