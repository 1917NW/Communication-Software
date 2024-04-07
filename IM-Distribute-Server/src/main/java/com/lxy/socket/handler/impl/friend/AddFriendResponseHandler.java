package com.lxy.socket.handler.impl.friend;

import cn.hutool.json.JSONUtil;
import com.lxy.application.UserService;
import com.lxy.domain.user.model.UserInfo;
import com.lxy.infrastructure.common.SocketChannelUtil;
import com.lxy.infrastructure.common.UserOffineMsgCache;
import com.lxy.protocolpackage.mq.MessageTopic;
import com.lxy.protocolpackage.protocol.friend.AddFriendResponse;
import com.lxy.protocolpackage.protocol.friend.FriendResponse;
import com.lxy.socket.handler.AbstractBizHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class AddFriendResponseHandler extends AbstractBizHandler<AddFriendResponse> {
    @Autowired
    private UserService userService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void channelRead(Channel channel, AddFriendResponse msg) {
        System.out.println("收到好友申请响应结果:"+ JSONUtil.toJsonStr(msg));
        // 同意就发送，不同意就不会发送
        if(msg.getAgree()){
            Channel requestChanenl = SocketChannelUtil.getChannel(msg.getRequestFriendId());
            // 不在线在保存，等待上线后再重新发送
            if(requestChanenl == null){
                rocketMQTemplate.send(MessageTopic.getAddFriendResponseTag(), MessageBuilder.withPayload(msg).build());
                return;
            }

            // 创建消息
            String userId = msg.getUserId();
            UserInfo userInfo = userService.queryUserInfo(userId);

            FriendResponse friendResponse = new FriendResponse();
            friendResponse.setAgreeFriendId(userInfo.getUserId());
            friendResponse.setAgreeFriendHead(userInfo.getUserHead());
            friendResponse.setAgreeFriendNickName(userInfo.getUserNickname());

            // 好友关系落库
            userService.asyncAddUserFriend(msg.getUserId(), msg.getRequestFriendId());



            // 在线，则直接发送消息
            System.out.println("发送好友申请成功响应:"+ JSONUtil.toJsonStr(friendResponse));
            requestChanenl.writeAndFlush(friendResponse);


        }
    }
}
