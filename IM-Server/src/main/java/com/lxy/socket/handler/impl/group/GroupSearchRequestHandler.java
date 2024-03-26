package com.lxy.socket.handler.impl.group;

import cn.hutool.json.JSONUtil;
import com.lxy.application.UserService;
import com.lxy.protocolpackage.protocol.group.GroupSearchRequest;
import com.lxy.protocolpackage.protocol.group.GroupSearchResponse;
import com.lxy.protocolpackage.protocol.group.dto.GroupDto;
import com.lxy.protocolpackage.protocol.login.LoginRequest;
import com.lxy.socket.handler.AbstractBizHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ChannelHandler.Sharable
public class GroupSearchRequestHandler extends AbstractBizHandler<GroupSearchRequest> {

    @Autowired
    private UserService userService;

    @Override
    public void channelRead(Channel channel, GroupSearchRequest msg) {
        System.out.println("收到群组搜索信息："+ JSONUtil.toJsonStr(msg));
        List<GroupDto> groupDtoList = userService.queryFuzzyGroupList(msg.getUserId(), msg.getMatchWord());
        GroupSearchResponse groupSearchResponse = new GroupSearchResponse(groupDtoList);
        channel.writeAndFlush(groupSearchResponse);

    }
}
