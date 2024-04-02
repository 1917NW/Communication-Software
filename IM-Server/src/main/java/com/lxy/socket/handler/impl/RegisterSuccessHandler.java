package com.lxy.socket.handler.impl;

import com.lxy.infrastructure.common.SocketChannelUtil;
import com.lxy.protocolpackage.protocol.login.LoginRequest;
import com.lxy.protocolpackage.protocol.register.RegisterSuccessRequest;
import com.lxy.socket.handler.AbstractBizHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class RegisterSuccessHandler extends AbstractBizHandler<RegisterSuccessRequest> {
    @Override
    public void channelRead(Channel channel, RegisterSuccessRequest msg) {
        // 保存个人Channel
        SocketChannelUtil.addChannel(msg.getUserId(), channel);
    }
}
