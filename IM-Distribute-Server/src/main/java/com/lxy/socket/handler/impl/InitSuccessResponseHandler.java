package com.lxy.socket.handler.impl;

import cn.hutool.json.JSONUtil;
import com.lxy.application.OfflineMsgService;
import com.lxy.infrastructure.common.UserOffineMsgCache;
import com.lxy.infrastructure.entity.ImOfflineMsg;
import com.lxy.protocolpackage.protocol.Packet;
import com.lxy.protocolpackage.protocol.init.InitSuccessResponse;
import com.lxy.protocolpackage.protocol.login.LoginRequest;
import com.lxy.socket.handler.AbstractBizHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ChannelHandler.Sharable
public class InitSuccessResponseHandler extends AbstractBizHandler<InitSuccessResponse> {

    @Autowired
    private OfflineMsgService offlineMsgService;


    @Override
    public void channelRead(Channel channel, InitSuccessResponse msg) {
        System.out.println("清空体现消息:"+msg.getUserId());
        // 先发送之前保存在数据库中的离线消息
        List<ImOfflineMsg> offlineMsgLocalList = offlineMsgService.getOfflineMsgByUserId(msg.getUserId());

        List<Packet> collect = offlineMsgLocalList.stream().map(offlineMsg -> {
            return JSONUtil.toBean(offlineMsg.getPacketJsonStr(), Packet.get(offlineMsg.getPacketType()));
        }).collect(Collectors.toList());

        if(collect != null && !collect.isEmpty()){
            for(Packet packet : collect){
                channel.writeAndFlush(packet);
            }
        }

    }
}
