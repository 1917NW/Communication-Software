package com.lxy.imapp.biz.socket.handler.impl;

import com.alibaba.fastjson.JSON;
import com.lxy.imapp.biz.file.ChatRecordMap;
import com.lxy.imapp.biz.socket.handler.AbstractBizHandler;
import com.lxy.imapp.biz.source.impl.FileDataSource;
import com.lxy.imapp.biz.threadPool.TaskExecutor;
import com.lxy.imapp.front.ImUI;
import com.lxy.imapp.front.controller.ChatController;
import com.lxy.protocolpackage.constants.MsgType;
import com.lxy.protocolpackage.constants.MsgUserType;
import com.lxy.protocolpackage.dto.ChatRecordDto;
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
        ChatController controller = imUI.getChat().controller;
        Platform.runLater(() -> {

            controller.addTalkMsgUserLeft(msg.getUserDto(), msg.getMsgText(), msg.getMsgType(), msg.getMsgDate(), true, false, true);
        });




        ChatRecordDto chatRecordDto = new ChatRecordDto();

        chatRecordDto.setTalkId(msg.getUserDto().getUserId());

        chatRecordDto.setUserId(msg.getUserDto().getUserId());
        chatRecordDto.setUserHead(msg.getUserDto().getUserHead());
        chatRecordDto.setUserNickName(msg.getUserDto().getUserNickName());

        chatRecordDto.setMsgContent(msg.getMsgText());
        chatRecordDto.setMsgDate(msg.getMsgDate());
        chatRecordDto.setMsgUserType(MsgUserType.OTHERS_MSG.getMsgTypeCode());
        chatRecordDto.setMsgType(msg.getMsgType());
        ChatRecordMap.appendChatRecordDto(chatRecordDto);


    }
}
