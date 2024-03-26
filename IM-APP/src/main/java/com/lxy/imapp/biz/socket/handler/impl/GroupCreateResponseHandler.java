package com.lxy.imapp.biz.socket.handler.impl;

import com.lxy.imapp.biz.socket.handler.AbstractBizHandler;
import com.lxy.imapp.front.ImUI;
import com.lxy.imapp.front.controller.ChatController;
import com.lxy.protocolpackage.protocol.group.GroupCreateResponse;
import com.lxy.protocolpackage.protocol.group.GroupSearchResponse;
import io.netty.channel.Channel;
import javafx.application.Platform;

public class GroupCreateResponseHandler extends AbstractBizHandler<GroupCreateResponse> {
    public GroupCreateResponseHandler(ImUI imUI) {
        this.imUI = imUI;
    }

    @Override
    public void channelRead(Channel channel, GroupCreateResponse msg) {
        Platform.runLater(() ->{
            ChatController controller = imUI.getChat().controller;
            controller.addFriendGroup(msg.getGroupId(), msg.getGroupName(),msg.getGroupHead());

        });
    }
}
