package com.lxy.socket.handler.impl.talk;

import cn.hutool.json.JSONUtil;
import com.lxy.application.UserService;
import com.lxy.protocolpackage.protocol.talk.DelTalkRequest;
import com.lxy.protocolpackage.protocol.talk.TalkNoticeRequest;
import com.lxy.socket.handler.AbstractBizHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class DelTalkRequestHandler extends AbstractBizHandler<DelTalkRequest> {

    @Autowired
    private UserService userService;

    @Override
    public void channelRead(Channel channel, DelTalkRequest msg) {
        System.out.println("服务器端删除对话:"+ JSONUtil.toJsonStr(msg));
        userService.deleteUserTalk(msg.getUserId(), msg.getTalkId(), msg.getTalkType());
    }
}
