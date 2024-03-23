package com.lxy.imapp.biz.socket.handler.impl;

import com.alibaba.fastjson.JSON;
import com.lxy.imapp.biz.socket.handler.AbstractBizHandler;
import com.lxy.imapp.front.ImUI;
import com.lxy.imapp.front.controller.ChatController;
import com.lxy.protocolpackage.protocol.friend.SearchFriendResponse;
import com.lxy.protocolpackage.protocol.friend.dto.UserDto;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.application.Platform;

import java.util.List;

public class SearchFriendHandler  extends AbstractBizHandler<SearchFriendResponse> {



    public SearchFriendHandler(ImUI imUI) {
        this.imUI = imUI;
    }

    @Override
    public void channelRead(Channel channel, SearchFriendResponse msg) {
        List<UserDto> list = msg.getList();
        System.out.println(JSON.toJSON(list));
        if(list == null || list.isEmpty())
            return;
        ChatController controller = imUI.getChat().controller;

        Platform.runLater(() -> {
            for(UserDto user : list){
                controller.addLuckFriend(user.getUserId(), user.getUserNickName(), user.getUserHead(), user.getStatus());
            }
        });


    }
}