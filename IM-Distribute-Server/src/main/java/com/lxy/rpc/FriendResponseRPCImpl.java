package com.lxy.rpc;

import com.lxy.application.OfflineMsgService;
import com.lxy.infrastructure.common.SocketChannelUtil;
import com.lxy.protocolpackage.protocol.friend.FriendResponse;
import com.lxy.protocolpackage.rpc.FriendResponseRPC;
import com.lxy.threadPool.TaskExecutor;
import io.netty.channel.Channel;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@DubboService
@Component
public class FriendResponseRPCImpl implements FriendResponseRPC {

    @Autowired
    private OfflineMsgService offlineMsgService;
    @Override
    public void sendToUserWithFriendResponse(String receiverId, FriendResponse friendResponse) {
        System.out.println("收到好友申请结果消息:id" + friendResponse);
        Channel channel = SocketChannelUtil.getChannel(receiverId);
        if(channel == null){
            TaskExecutor.execTask(()->{
                System.out.println("userId:"+receiverId+"不在线,开始缓存");
                offlineMsgService.storeOfflineMsg(receiverId, friendResponse);
            });
            return;
        }

        channel.writeAndFlush(friendResponse);
    }
}
