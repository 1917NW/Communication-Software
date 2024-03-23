package com.lxy.imapp.biz.socket.handler.impl;

import com.alibaba.fastjson.JSON;
import com.lxy.imapp.biz.socket.handler.AbstractBizHandler;
import com.lxy.imapp.front.ImUI;
import com.lxy.imapp.front.constant.TalkType;
import com.lxy.imapp.front.controller.ChatController;
import com.lxy.imapp.front.view.Chat;
import com.lxy.protocolpackage.constants.MsgType;
import com.lxy.protocolpackage.protocol.login.LoginRequest;
import com.lxy.protocolpackage.protocol.login.LoginResponse;
import com.lxy.protocolpackage.protocol.login.dto.ChatRecordDto;
import com.lxy.protocolpackage.protocol.login.dto.ChatTalkDto;
import com.lxy.protocolpackage.protocol.login.dto.GroupsDto;
import com.lxy.protocolpackage.protocol.login.dto.UserFriendDto;
import io.netty.channel.Channel;
import javafx.application.Platform;

import java.util.List;


public class LoginHandler extends AbstractBizHandler<LoginResponse> {



    public LoginHandler(ImUI imUI) {
        this.imUI = imUI;
    }

    @Override
    public void channelRead(Channel channel, LoginResponse msg) {

        System.out.println("登录消息响应:" + JSON.toJSON(msg));

        if (!msg.isSuccess()) {
            System.out.println("登录失败");
            imUI.getLogin().LoingFailed();
            return;
        }

        Platform.runLater(() -> {
            imUI.getLogin().LoginSuccess();
            ChatController chatController = imUI.getChat().controller;
            System.out.println(msg);
            chatController.setUserInfo("123", "lxy", "");

            // 显示对话
            List<ChatTalkDto> chatTalkList = msg.getChatTalkList();
            if(chatTalkList != null){
                chatTalkList.forEach(talk -> {
                    chatController.addTalkBox(0, talk.getTalkType(), talk.getTalkId(), talk.getTalkName(), talk.getTalkHead(), talk.getTalkSketch(), talk.getTalkDate(), false);
                    List<ChatRecordDto> chatRecordList = talk.getChatRecordList();
                    if(talk.getTalkType().equals(TalkType.PRIVATE_MESSAGE.getTalkTypeCode())){

                        // 如果为空，则直接返回
                        if(chatRecordList == null || chatRecordList.isEmpty())
                            return;

                        chatRecordList.forEach(chatRecord ->{
                            // 如果是自己发送的消息
                            if(chatRecord.getMsgType().equals(MsgType.MINE_MSG.getMsgTypeCode())){
                                chatController.addTalkMsgRight(chatRecord.getTalkId(), chatRecord.getMsgContent(),  chatRecord.getMsgDate(), true,false, false);
                            }else if(chatRecord.getMsgType().equals(MsgType.OTHERS_MSG.getMsgTypeCode())){
                                chatController.addTalkMsgUserLeft(chatRecord.getTalkId(), chatRecord.getMsgContent(),  chatRecord.getMsgDate(), true,false, false);
                            }
                        } );

                    }
                    else if(talk.getTalkType().equals(TalkType.GROUP_MESSAGE.getTalkTypeCode())){
                        // 如果为空，则直接返回
                        if(chatRecordList == null || chatRecordList.isEmpty())
                            return;

                        chatRecordList.forEach(chatRecord ->{
                            // 如果是自己发送的消息
                            if(chatRecord.getMsgType().equals(MsgType.MINE_MSG.getMsgTypeCode())){
                                chatController.addTalkMsgRight(chatRecord.getTalkId(), chatRecord.getMsgContent(),  chatRecord.getMsgDate(), true,false, false);
                            }else if(chatRecord.getMsgType().equals(MsgType.OTHERS_MSG.getMsgTypeCode())){
                                chatController.addTalkMsgGroupLeft(chatRecord.getTalkId(), chatRecord.getUserId(), chatRecord.getUserNickName(), chatRecord.getUserHead(), chatRecord.getMsgContent(),chatRecord.getMsgDate(),true,false, false);
                            }
                        } );
                    }
                });
            }

            // 显示群组
            List<GroupsDto> groupsList = msg.getGroupsList();
            if(groupsList != null){
                groupsList.forEach(group -> {
                    chatController.addFriendGroup(group.getGroupId(), group.getGroupName(), group.getGroupHead());
                });
            }

            // 显示好友
            List<UserFriendDto> userFriendList = msg.getUserFriendList();
            if(userFriendList != null){
                userFriendList.forEach(friend ->{
                    chatController.addFriendUser(false, friend.getFriendId(), friend.getFriendName(), friend.getFriendHead());

                });
            }

        });

    }
}