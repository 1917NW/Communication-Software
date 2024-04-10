package com.lxy.socket.handler.impl;

import com.lxy.infrastructure.common.SocketChannelUtil;
import com.lxy.protocolpackage.protocol.register.RegisterSuccessRequest;
import com.lxy.protocolpackage.rediskey.ImServerKey;
import com.lxy.socket.cache.ImServerCache;
import com.lxy.socket.handler.AbstractBizHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class RegisterSuccessHandler extends AbstractBizHandler<RegisterSuccessRequest> {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void channelRead(Channel channel, RegisterSuccessRequest msg) {
        // 保存个人Channel
        SocketChannelUtil.addChannel(msg.getUserId(), channel);

        // 注册userId:dubboServiceUrl到redis
        stringRedisTemplate.opsForValue().set(ImServerKey.buildUserIdKey(msg.getUserId()), ImServerCache.getDubboServiceUrl());

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }
}
