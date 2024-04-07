package com.lxy.rpc;

import com.lxy.application.OfflineMsgService;
import com.lxy.infrastructure.common.SocketChannelUtil;
import com.lxy.protocolpackage.protocol.group.JoinInGroupResponse;
import com.lxy.protocolpackage.rpc.JoinInGroupResponseRPC;
import com.lxy.threadPool.TaskExecutor;
import io.netty.channel.Channel;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@DubboService
@Component
public class JoinInGroupResponseRPCImpl implements JoinInGroupResponseRPC {

    @Autowired
    private OfflineMsgService offlineMsgService;
    @Override
    public void sendToUserWithJoinInGroupResponse(String receiverId, JoinInGroupResponse joinInGroupResponse) {
        System.out.println("收到群组申请结果" + joinInGroupResponse);
        Channel channel = SocketChannelUtil.getChannel(receiverId);
        if(channel == null){
            TaskExecutor.execTask(()->{
                System.out.println("userId:"+receiverId+"不在线,开始缓存");
                offlineMsgService.storeOfflineMsg(receiverId, joinInGroupResponse);
            });
            return;
        }

        channel.writeAndFlush(joinInGroupResponse);
    }
}
