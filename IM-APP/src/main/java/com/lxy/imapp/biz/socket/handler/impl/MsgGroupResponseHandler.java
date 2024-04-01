package com.lxy.imapp.biz.socket.handler.impl;

import com.alibaba.fastjson.JSON;
import com.lxy.imapp.biz.socket.handler.AbstractBizHandler;
import com.lxy.imapp.biz.source.impl.FileDataSource;
import com.lxy.imapp.front.ImUI;
import com.lxy.imapp.front.controller.ChatController;
import com.lxy.protocolpackage.constants.MsgType;
import com.lxy.protocolpackage.constants.MsgUserType;
import com.lxy.protocolpackage.dto.ChatRecordDto;
import com.lxy.protocolpackage.protocol.group.MsgGroupResponse;

import io.netty.channel.Channel;
import javafx.application.Platform;

public class MsgGroupResponseHandler extends AbstractBizHandler<MsgGroupResponse> {

    public MsgGroupResponseHandler(ImUI imUI) {
        this.imUI = imUI;
    }
    @Override
    public void channelRead(Channel channel, MsgGroupResponse msg) {
        System.out.println("收到群组消息"+ JSON.toJSON(msg));
        Platform.runLater(() -> {
            ChatController controller = imUI.getChat().controller;
            controller.addTalkMsgGroupLeft(msg.getGroupId(), msg.getGroupName(), msg.getGroupHead(), msg.getUserId(), msg.getUserNickName(), msg.getUserHead(), msg.getMsg(), msg.getMsgType(),msg.getMsgDate(), true, false, true);

            // 写入文件
            FileDataSource fileDataSource = new FileDataSource(controller.currentUserId);

            ChatRecordDto chatRecordDto = new ChatRecordDto();

            chatRecordDto.setTalkId(msg.getGroupId());

            chatRecordDto.setUserId(msg.getUserId());
            chatRecordDto.setUserHead(msg.getUserHead());
            chatRecordDto.setUserNickName(msg.getUserNickName());

            chatRecordDto.setMsgContent(msg.getMsg());
            chatRecordDto.setMsgDate(msg.getMsgDate());
            chatRecordDto.setMsgUserType(MsgUserType.OTHERS_MSG.getMsgTypeCode());
            chatRecordDto.setMsgType(msg.getMsgType());

            System.out.println("写入文件:"+ chatRecordDto);
            fileDataSource.addChatRecordDto(chatRecordDto);
        });
    }
}
