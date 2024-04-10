package com.lxy.socket.handler.impl.group;

import com.lxy.application.UserService;
import com.lxy.infrastructure.common.SocketChannelUtil;
import com.lxy.protocolpackage.protocol.group.ExitGroupRequest;
import com.lxy.protocolpackage.protocol.group.GroupCreateRequest;
import com.lxy.socket.handler.AbstractBizHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class ExitGroupRequestHandler extends AbstractBizHandler<ExitGroupRequest> {

    @Autowired
    private UserService userService;

    @Override
    public void channelRead(Channel channel, ExitGroupRequest msg) {
        SocketChannelUtil.removeChannelFromGroup(msg.getDeletedGroupId(), channel);
        userService.exitGroup(msg.getInvokerId(), msg.getDeletedGroupId());
    }
}
