package com.lxy.socket.handler.impl.group;

import com.lxy.application.UserService;
import com.lxy.domain.user.model.GroupsInfo;
import com.lxy.protocolpackage.protocol.group.GroupCreateRequest;
import com.lxy.protocolpackage.protocol.group.GroupCreateResponse;
import com.lxy.protocolpackage.protocol.group.GroupSearchRequest;
import com.lxy.socket.handler.AbstractBizHandler;
import com.lxy.socket.util.GroupIdGenerator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class GroupCreateRequestHandler extends AbstractBizHandler<GroupCreateRequest> {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupIdGenerator groupIdGenerator;

    @Override
    public void channelRead(Channel channel, GroupCreateRequest msg) {

        System.out.println("收到创建群组消息:"+msg);

        String groupId = groupIdGenerator.nextId();
        userService.createGroup(new GroupsInfo(groupId, msg.getGroupName(), msg.getGroupHead(), msg.getGroupLeaderId()));
        userService.addUserToGroup(msg.getGroupLeaderId(), groupId);

        GroupCreateResponse groupCreateResponse = new GroupCreateResponse();
        groupCreateResponse.setGroupId(groupId);

        groupCreateResponse.setGroupName(msg.getGroupName());
        groupCreateResponse.setGroupHead(msg.getGroupHead());
        channel.writeAndFlush(groupCreateResponse);
    }
}
