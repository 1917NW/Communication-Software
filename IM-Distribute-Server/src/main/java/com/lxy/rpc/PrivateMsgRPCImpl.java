package com.lxy.rpc;

import com.lxy.application.OfflineMsgService;
import com.lxy.infrastructure.common.SocketChannelUtil;
import com.lxy.protocolpackage.protocol.msg.MsgResponse;
import com.lxy.protocolpackage.rpc.PrivateMsgRPC;
import com.lxy.threadPool.TaskExecutor;
import io.netty.channel.Channel;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@DubboService
@Component
public class PrivateMsgRPCImpl implements PrivateMsgRPC {

    @Autowired
    private OfflineMsgService offlineMsgService;

    @Override
    public void sendToUser(String receiverId, MsgResponse msgResponse) {

        System.out.println("收到转发消息:id" + msgResponse);
        Channel channel = SocketChannelUtil.getChannel(receiverId);

        if(channel == null){
            TaskExecutor.execTask(()->{
                System.out.println("userId:"+receiverId+"不在线,开始缓存");
                offlineMsgService.storeOfflineMsg(receiverId, msgResponse);
            });
            return;
        }
        channel.writeAndFlush(msgResponse);

    }
}
