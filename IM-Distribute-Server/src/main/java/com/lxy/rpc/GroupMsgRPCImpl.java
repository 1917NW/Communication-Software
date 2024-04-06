package com.lxy.rpc;

import com.lxy.infrastructure.common.SocketChannelUtil;
import com.lxy.protocolpackage.protocol.group.MsgGroupResponse;
import com.lxy.protocolpackage.rpc.GroupMsgRPC;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

@DubboService
@Component
public class GroupMsgRPCImpl implements GroupMsgRPC {
    @Override
    public void sendToGroup(String groupId, MsgGroupResponse msgGroupResponse) {

        ChannelGroup channelGroup = SocketChannelUtil.getChannelGroupById(groupId);

        // 群组消息就不做在线判断了
        if(channelGroup != null){
            channelGroup.writeAndFlush(msgGroupResponse);
        }

    }
}
