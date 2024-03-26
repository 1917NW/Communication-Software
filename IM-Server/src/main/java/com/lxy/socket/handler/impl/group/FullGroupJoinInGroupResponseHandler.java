package com.lxy.socket.handler.impl.group;

import cn.hutool.json.JSONUtil;
import com.lxy.application.UserService;
import com.lxy.infrastructure.common.SocketChannelUtil;
import com.lxy.infrastructure.common.UserOffineMsgCache;
import com.lxy.protocolpackage.protocol.group.FullGroupJoinInGroupResponse;
import com.lxy.protocolpackage.protocol.group.JoinInGroupRequest;
import com.lxy.protocolpackage.protocol.group.JoinInGroupResponse;
import com.lxy.socket.handler.AbstractBizHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class FullGroupJoinInGroupResponseHandler extends AbstractBizHandler<FullGroupJoinInGroupResponse> {
    @Autowired
    private UserService userService;

    @Override
    public void channelRead(Channel channel, FullGroupJoinInGroupResponse msg) {

        System.out.println("收到群组申请响应:"+ JSONUtil.toJsonStr(msg));
        if(msg.getAgree()){
            // 加入群组
            userService.addUserToGroup(msg.getUserId(), msg.getGroupDto().getGroupId());

            JoinInGroupResponse joinInGroupResponse = new JoinInGroupResponse();
            joinInGroupResponse.setGroupDto(msg.getGroupDto());

            Channel requestUserChannel = SocketChannelUtil.getChannel(msg.getUserId());
            if(requestUserChannel == null){
                UserOffineMsgCache.addOfflineMsgToUser(msg.getUserId(), joinInGroupResponse);
                return;
            }

            requestUserChannel.writeAndFlush(joinInGroupResponse);

        }
    }
}
