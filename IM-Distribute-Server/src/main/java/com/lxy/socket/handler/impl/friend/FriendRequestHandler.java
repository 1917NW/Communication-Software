package com.lxy.socket.handler.impl.friend;

import cn.hutool.json.JSONUtil;
import com.lxy.application.UserService;
import com.lxy.domain.user.model.UserInfo;
import com.lxy.infrastructure.common.SocketChannelUtil;
import com.lxy.infrastructure.common.UserOffineMsgCache;
import com.lxy.protocolpackage.mq.MessageTopic;
import com.lxy.protocolpackage.protocol.friend.AddFriendRequest;
import com.lxy.protocolpackage.protocol.friend.FriendRequest;
import com.lxy.socket.handler.AbstractBizHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class FriendRequestHandler extends AbstractBizHandler<FriendRequest> {

    @Autowired
    private UserService userService;


    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void channelRead(Channel channel, FriendRequest msg) {
        System.out.println("收到好友申请:" + JSONUtil.toJsonStr(msg));

        Channel friendChannel = SocketChannelUtil.getChannel(msg.getFriendId());
        // 如果不在线，则保存到缓存
        if(friendChannel == null){
            rocketMQTemplate.send(MessageTopic.getFriendRequestTag(), MessageBuilder.withPayload(msg).build());
            return;
        }

        UserInfo userInfo = userService.queryUserInfo(msg.getUserId());

        AddFriendRequest addFriendRequest = new AddFriendRequest();
        addFriendRequest.setRequestFriendId(userInfo.getUserId());
        addFriendRequest.setRequestFriendNickName(userInfo.getUserNickname());
        addFriendRequest.setRequestFriendHead(userInfo.getUserHead());



        friendChannel.writeAndFlush(addFriendRequest);
    }
}
