package com.lxy.socket.handler.impl.group;

import com.lxy.application.UserService;
import com.lxy.domain.user.model.ChatRecordInfo;
import com.lxy.infrastructure.common.SocketChannelUtil;
import com.lxy.protocolpackage.constants.TalkType;
import com.lxy.protocolpackage.dto.GroupDto;
import com.lxy.protocolpackage.dto.UserDto;
import com.lxy.protocolpackage.protocol.group.MsgGroupRequest;
import com.lxy.protocolpackage.protocol.group.MsgGroupResponse;

import com.lxy.socket.handler.AbstractBizHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.group.ChannelGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ChannelHandler.Sharable
public class MsgGroupRequestHandler extends AbstractBizHandler<MsgGroupRequest> {

    @Autowired
    private UserService userService;

    @Override
    public void channelRead(Channel channel, MsgGroupRequest msg) {
        ChannelGroup channelGroup = SocketChannelUtil.getChannelGroupById(msg.getGroupId());
        if(channelGroup == null){
            SocketChannelUtil.addChannelGroup(msg.getGroupId(), channel);
            channelGroup = SocketChannelUtil.getChannelGroupById(msg.getGroupId());
        }
        // 异步写库
        userService.asyncAppendChatRecord(new ChatRecordInfo(msg.getUserDto().getUserId(), msg.getGroupId(), msg.getMsgText(), msg.getMsgType(), msg.getMsgDate(), TalkType.GROUP_MESSAGE.getTalkTypeCode()));

        // 获取某个组内的所有用户id
        List<String> userIdListByGroupId = userService.getUserIdListByGroupId(msg.getGroupId());

        // 群组内的所有人都会添加一个会话
        userService.addTalkBoxInfoBatch(userIdListByGroupId, msg.getGroupId(), TalkType.GROUP_MESSAGE.getTalkTypeCode());

        GroupDto groupDto = userService.queryGroupInfo(msg.getGroupId());

        // 群发消息
        MsgGroupResponse msgGroupResponse = new MsgGroupResponse();
        UserDto userDto = msg.getUserDto();

        msgGroupResponse.setGroupId(msg.getGroupId());
        msgGroupResponse.setGroupName(groupDto.getGroupName());
        msgGroupResponse.setGroupHead(groupDto.getGroupHead());

        msgGroupResponse.setUserId(userDto.getUserId());
        msgGroupResponse.setUserHead(userDto.getUserHead());
        msgGroupResponse.setUserNickName(userDto.getUserNickName());
        msgGroupResponse.setMsg(msg.getMsgText());
        msgGroupResponse.setMsgType(msg.getMsgType());
        msgGroupResponse.setMsgDate(msg.getMsgDate());
        channelGroup.writeAndFlush(msgGroupResponse);

    }
}
