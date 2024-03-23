package com.lxy.imapp.biz.socket.handler.impl;

import com.alibaba.fastjson.JSON;
import com.lxy.imapp.biz.socket.handler.AbstractBizHandler;
import com.lxy.imapp.front.ImUI;
import com.lxy.imapp.front.controller.ChatController;
import com.lxy.protocolpackage.protocol.friend.AddFriendRequest;
import com.lxy.protocolpackage.protocol.login.LoginResponse;
import io.netty.channel.Channel;
import javafx.application.Platform;

public class AddFriendRequestHandler  extends AbstractBizHandler<AddFriendRequest> {

    public AddFriendRequestHandler(ImUI imUI){this.imUI = imUI;}
    @Override
    public void channelRead(Channel channel, AddFriendRequest msg) {
        System.out.println("收到好友申请:" + JSON.toJSON(msg));
        Platform.runLater(() -> {
            ChatController controller = imUI.getChat().controller;
            controller.remindNewFriend(msg.getRequestFriendId(), msg.getRequestFriendNickName(), msg.getRequestFriendHead());


        });
    }
}
