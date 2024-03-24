package com.lxy.imapp.biz.socket.handler.impl;

import com.alibaba.fastjson.JSON;
import com.lxy.imapp.biz.socket.handler.AbstractBizHandler;
import com.lxy.imapp.front.ImUI;
import com.lxy.imapp.front.controller.ChatController;
import com.lxy.protocolpackage.protocol.friend.FriendResponse;
import com.lxy.protocolpackage.protocol.msg.MsgResponse;
import io.netty.channel.Channel;
import javafx.application.Platform;

public class MsgResponseHandler extends AbstractBizHandler<MsgResponse> {

    public MsgResponseHandler(ImUI imUI) {
        this.imUI = imUI;
    }

    @Override
    public void channelRead(Channel channel, MsgResponse msg) {
        System.out.println("收到好友的消息:"+ JSON.toJSON(msg));
        Platform.runLater(() -> {
            ChatController controller = imUI.getChat().controller;
            controller.addTalkMsgUserLeft(msg.getUserDto(), msg.getMsgText(), msg.getMsgDate(), true, false, true);

        });
    }
}
