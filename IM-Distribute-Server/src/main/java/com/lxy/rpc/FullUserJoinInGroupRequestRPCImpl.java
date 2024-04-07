package com.lxy.rpc;

import com.lxy.application.OfflineMsgService;
import com.lxy.infrastructure.common.SocketChannelUtil;
import com.lxy.protocolpackage.protocol.group.FullUserJoinInGroupRequest;
import com.lxy.protocolpackage.rpc.FullUserJoinInGroupRequestRPC;
import com.lxy.threadPool.TaskExecutor;
import io.netty.channel.Channel;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@DubboService
@Component
public class FullUserJoinInGroupRequestRPCImpl implements FullUserJoinInGroupRequestRPC {

    @Autowired
    private OfflineMsgService offlineMsgService;
    @Override
    public void sendToUserWithFullUserJoinInGroupRequest(String receiverId, FullUserJoinInGroupRequest fullUserJoinInGroupRequest) {
        System.out.println("收到好友申请结果消息:id" + fullUserJoinInGroupRequest);
        Channel channel = SocketChannelUtil.getChannel(receiverId);
        if(channel == null){
            TaskExecutor.execTask(()->{
                System.out.println("userId:"+receiverId+"不在线,开始缓存");
                offlineMsgService.storeOfflineMsg(receiverId, fullUserJoinInGroupRequest);
            });
            return;
        }

        channel.writeAndFlush(fullUserJoinInGroupRequest);
    }
}
