package com.lxy.imapp.biz.socket.handler.impl;

import com.lxy.imapp.biz.socket.handler.AbstractBizHandler;
import com.lxy.imapp.front.ImUI;
import com.lxy.imapp.front.controller.ChatController;
import com.lxy.protocolpackage.protocol.group.FullUserJoinInGroupRequest;
import com.lxy.protocolpackage.protocol.group.GroupCreateResponse;
import io.netty.channel.Channel;
import javafx.application.Platform;

public class FullUserJoinInGroupRequestHandler extends AbstractBizHandler<FullUserJoinInGroupRequest> {
    public FullUserJoinInGroupRequestHandler(ImUI imUI) {
        this.imUI = imUI;
    }
    @Override
    public void channelRead(Channel channel, FullUserJoinInGroupRequest msg) {
        System.out.println("收到群组申请");
        Platform.runLater(() ->{
            ChatController controller = imUI.getChat().controller;
            controller.remindGroupRequest(msg.getUserDto(), msg.getGroupDto());
        });
    }
}
