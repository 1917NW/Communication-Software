package com.lxy.socket.handler.impl.msg;

import cn.hutool.json.JSONUtil;
import com.lxy.application.UserService;
import com.lxy.domain.user.model.ChatRecordInfo;
import com.lxy.infrastructure.common.SocketChannelUtil;
import com.lxy.infrastructure.common.UserOffineMsgCache;
import com.lxy.protocolpackage.constants.TalkType;
import com.lxy.protocolpackage.protocol.friend.SearchFriendRequest;
import com.lxy.protocolpackage.protocol.friend.dto.UserDto;
import com.lxy.protocolpackage.protocol.msg.MsgRequest;
import com.lxy.protocolpackage.protocol.msg.MsgResponse;
import com.lxy.socket.handler.AbstractBizHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
@Slf4j
public class MsgRequestHandler extends AbstractBizHandler<MsgRequest> {

    @Autowired
    private UserService userService;

    @Override
    public void channelRead(Channel channel, MsgRequest msg) {
        log.info("消息转发:{}", JSONUtil.toJsonStr(msg));
        UserDto sender = msg.getUserDto();
        userService.asyncAppendChatRecord(new ChatRecordInfo(sender.getUserId(), msg.getFriendId(), msg.getMsgText(), msg.getMsgType(),msg.getMsgDate(), TalkType.PRIVATE_MESSAGE.getTalkTypeCode()));
        userService.addTalkBoxInfo(msg.getFriendId(), sender.getUserId(), TalkType.PRIVATE_MESSAGE.getTalkTypeCode());

        // 组装消息
        MsgResponse msgResponse = new MsgResponse();
        msgResponse.setMsgDate(msg.getMsgDate());
        msgResponse.setMsgText(msg.getMsgText());
        msgResponse.setMsgType(msg.getMsgType());
        msgResponse.setUserDto(sender);

        Channel friendChannel = SocketChannelUtil.getChannel(msg.getFriendId());
        if(friendChannel == null){
            log.info("用户id:{}未登录", msg.getFriendId());
            UserOffineMsgCache.addOfflineMsgToUser(msg.getFriendId(),msgResponse);
            return;
        }

        friendChannel.writeAndFlush(msgResponse);

    }
}
