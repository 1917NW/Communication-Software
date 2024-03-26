package com.lxy.imapp.biz.socket.handler.impl;

import com.lxy.imapp.biz.socket.handler.AbstractBizHandler;
import com.lxy.imapp.front.ImUI;
import com.lxy.imapp.front.controller.ChatController;
import com.lxy.protocolpackage.protocol.group.JoinInGroupResponse;
import com.lxy.protocolpackage.protocol.group.dto.GroupDto;
import com.lxy.protocolpackage.protocol.login.LoginResponse;
import io.netty.channel.Channel;
import javafx.application.Platform;

public class JoinInGroupResponseHandler extends AbstractBizHandler<JoinInGroupResponse> {

    public JoinInGroupResponseHandler(ImUI imUI) {
        this.imUI = imUI;
    }
    
    @Override
    public void channelRead(Channel channel, JoinInGroupResponse msg) {
        Platform.runLater(() -> {
            ChatController controller = imUI.getChat().controller;
            GroupDto group = msg.getGroupDto();
            controller.addFriendGroup(group.getGroupId(), group.getGroupName(), group.getGroupHead());
        });
    }
}
