package com.lxy.socket.handler.impl.group;

import cn.hutool.json.JSONUtil;
import com.lxy.application.UserService;
import com.lxy.infrastructure.common.SocketChannelUtil;
import com.lxy.infrastructure.common.UserOffineMsgCache;
import com.lxy.protocolpackage.mq.MessageTopic;
import com.lxy.protocolpackage.protocol.group.FullGroupJoinInGroupResponse;
import com.lxy.protocolpackage.protocol.group.JoinInGroupResponse;
import com.lxy.socket.handler.AbstractBizHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class FullGroupJoinInGroupResponseHandler extends AbstractBizHandler<FullGroupJoinInGroupResponse> {
    @Autowired
    private UserService userService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void channelRead(Channel channel, FullGroupJoinInGroupResponse msg) {

        System.out.println("收到群组申请响应:"+ JSONUtil.toJsonStr(msg));
        if(msg.getAgree()){
            Channel requestUserChannel = SocketChannelUtil.getChannel(msg.getUserId());
            SocketChannelUtil.addChannelGroup(msg.getGroupDto().getGroupId(), requestUserChannel);
            if(requestUserChannel == null){
                rocketMQTemplate.send(MessageTopic.getFullGroupJoinInGroupResponseTag(), MessageBuilder.withPayload(msg).build());
                return;
            }

            // 加入群组
            userService.addUserToGroup(msg.getUserId(), msg.getGroupDto().getGroupId());

            JoinInGroupResponse joinInGroupResponse = new JoinInGroupResponse();
            joinInGroupResponse.setGroupDto(msg.getGroupDto());




            requestUserChannel.writeAndFlush(joinInGroupResponse);

        }
    }
}
