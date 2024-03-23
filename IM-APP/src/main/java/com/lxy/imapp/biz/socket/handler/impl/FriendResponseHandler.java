package com.lxy.imapp.biz.socket.handler.impl;

import com.lxy.imapp.biz.socket.handler.AbstractBizHandler;
import com.lxy.imapp.front.ImUI;
import com.lxy.imapp.front.controller.ChatController;
import com.lxy.protocolpackage.protocol.friend.FriendResponse;
import com.lxy.protocolpackage.protocol.login.LoginResponse;
import io.netty.channel.Channel;
import javafx.application.Platform;

public class FriendResponseHandler extends AbstractBizHandler<FriendResponse> {

    public FriendResponseHandler(ImUI imUI) {
        this.imUI = imUI;
    }


    @Override
    public void channelRead(Channel channel, FriendResponse msg) {

        Platform.runLater(() -> {
            ChatController controller = imUI.getChat().controller;
            controller.addFriendUser(false, msg.getAgreeFriendId(), msg.getAgreeFriendNickName(), msg.getAgreeFriendHead());
        });


    }
}
