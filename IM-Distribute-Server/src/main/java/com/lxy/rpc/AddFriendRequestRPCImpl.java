package com.lxy.rpc;


import com.lxy.application.OfflineMsgService;
import com.lxy.infrastructure.common.SocketChannelUtil;
import com.lxy.protocolpackage.protocol.friend.AddFriendRequest;
import com.lxy.protocolpackage.rpc.AddFriendRequestRPC;
import com.lxy.threadPool.TaskExecutor;
import io.netty.channel.Channel;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@DubboService
@Component
public class AddFriendRequestRPCImpl implements AddFriendRequestRPC {

    @Autowired
    private OfflineMsgService offlineMsgService;

    @Override
    public void sendToUserWithFriendRequest(String receiverId, AddFriendRequest addFriendRequest) {
        System.out.println("收到好友申请消息:id" + addFriendRequest);
        Channel channel = SocketChannelUtil.getChannel(receiverId);
        if(channel == null){
            TaskExecutor.execTask(()->{
                System.out.println("userId:"+receiverId+"不在线,开始缓存");
                offlineMsgService.storeOfflineMsg(receiverId, addFriendRequest);
            });
            return;
        }

        channel.writeAndFlush(addFriendRequest);
    }
}
