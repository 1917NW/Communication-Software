package com.lxy.rpc;

import com.lxy.infrastructure.common.SocketChannelUtil;
import com.lxy.protocolpackage.protocol.friend.DeleteFriendResponse;
import com.lxy.protocolpackage.rpc.DeleteFriendResponseRPC;
import com.lxy.threadPool.TaskExecutor;
import io.netty.channel.Channel;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

@DubboService
@Component
public class DeleteFriendResponseRPCImpl implements DeleteFriendResponseRPC {
    @Override
    public void sendToUser(String receiverId, DeleteFriendResponse deleteFriendResponse) {
        System.out.println("收到好友申请消息:id" + deleteFriendResponse);
        Channel channel = SocketChannelUtil.getChannel(receiverId);
        if(channel == null){
            return;
        }

        channel.writeAndFlush(deleteFriendResponse);
    }
}
