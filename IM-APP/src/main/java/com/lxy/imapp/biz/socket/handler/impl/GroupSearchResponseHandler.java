package com.lxy.imapp.biz.socket.handler.impl;

import com.lxy.imapp.biz.socket.handler.AbstractBizHandler;
import com.lxy.imapp.front.ImUI;
import com.lxy.imapp.front.controller.ChatController;
import com.lxy.protocolpackage.protocol.group.GroupSearchResponse;
import com.lxy.protocolpackage.protocol.group.dto.GroupDto;
import com.lxy.protocolpackage.protocol.login.LoginResponse;
import io.netty.channel.Channel;
import javafx.application.Platform;

import java.util.List;

public class GroupSearchResponseHandler extends AbstractBizHandler<GroupSearchResponse> {

    public GroupSearchResponseHandler(ImUI imUI) {
        this.imUI = imUI;
    }
    @Override
    public void channelRead(Channel channel, GroupSearchResponse msg) {
        List<GroupDto> groupDtoList = msg.getGroupDtoList();
        if(groupDtoList == null || groupDtoList.isEmpty())
            return;
        Platform.runLater(() -> {
            ChatController controller = imUI.getChat().controller;
            for(GroupDto groupDto : groupDtoList){
                controller.addSearchGroup(groupDto);
            }
        });

    }
}
