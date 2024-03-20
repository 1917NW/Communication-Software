package com.lxy.imapp.biz.socket.handler.impl;

import com.alibaba.fastjson.JSON;
import com.lxy.imapp.biz.socket.handler.AbstractBizHandler;
import com.lxy.imapp.front.ImUI;
import com.lxy.imapp.front.controller.ChatController;
import com.lxy.imapp.front.view.Chat;
import com.lxy.protocolpackage.protocol.login.LoginRequest;
import com.lxy.protocolpackage.protocol.login.LoginResponse;
import io.netty.channel.Channel;
import javafx.application.Platform;


public class LoginHandler extends AbstractBizHandler<LoginResponse> {

    private ImUI imUI;

    public LoginHandler(ImUI imUI) {
        this.imUI = imUI;
    }

    @Override
    public void channelRead(Channel channel, LoginResponse msg) {

        System.out.println("登录消息响应:" + JSON.toJSON(msg));

        if (!msg.isSuccess()) {
            System.out.println("登录失败");
        }

        Platform.runLater(() -> {
            imUI.getLogin().LoginSuccess();
            ChatController chatController = imUI.getChat().controller;
            System.out.println(msg);
            chatController.setUserInfo("123", "lxy", "");

        });

    }
}