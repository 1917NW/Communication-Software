package com.lxy.imapp.biz.socket.handler.impl;

import com.alibaba.fastjson.JSON;

import com.lxy.imapp.biz.file.ChatRecordMap;
import com.lxy.imapp.biz.socket.handler.AbstractBizHandler;
import com.lxy.imapp.biz.source.impl.FileDataSource;
import com.lxy.imapp.front.ImUI;
import com.lxy.imapp.front.controller.ChatController;
import com.lxy.imapp.front.data.TalkBoxData;

import com.lxy.protocolpackage.constants.MsgUserType;
import com.lxy.protocolpackage.constants.TalkType;
import com.lxy.protocolpackage.dto.ChatRecordDto;
import com.lxy.protocolpackage.dto.GroupsDto;
import com.lxy.protocolpackage.dto.UserDto;
import com.lxy.protocolpackage.dto.UserFriendDto;
import com.lxy.protocolpackage.protocol.login.LoginResponse;

import io.netty.channel.Channel;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

            chatController.setUserInfo(msg.getUserId(), msg.getUserNickName(), msg.getUserHead());

            // 显示对话

            List<TalkBoxData> chatTalkList = getTalkList(msg.getUserId());
            Map<String, List<ChatRecordDto>> chatRecordMap = getChatRecordMap(msg.getUserId());
            if(chatTalkList != null){
                chatTalkList.forEach(talk -> {
                    chatController.addTalkBox(0, talk.getTalkType(), talk.getTalkId(), talk.getTalkName(), talk.getTalkHead(), "", null, false);
                    List<ChatRecordDto> chatRecordList = chatRecordMap.get(talk.getTalkId());
                    if(talk.getTalkType().equals(TalkType.PRIVATE_MESSAGE.getTalkTypeCode())){

                        UserDto userDto = new UserDto();
                        userDto.setUserId(talk.getTalkId());
                        userDto.setUserHead(talk.getTalkHead());
                        userDto.setUserNickName(talk.getTalkName());

                        // 如果为空，则直接返回
                        if(chatRecordList == null || chatRecordList.isEmpty())
                            return;

                        chatRecordList.forEach(chatRecord ->{
                            // 如果是自己发送的消息
                            if(!chatRecord.getMsgUserType().equals(MsgUserType.OTHERS_MSG.getMsgTypeCode())){
                                chatController.addTalkMsgRight(chatRecord.getTalkId(), chatRecord.getMsgContent(), chatRecord.getMsgType(), chatRecord.getMsgDate(), true,false, false);
                            }else {
                                chatController.addTalkMsgUserLeft(userDto, chatRecord.getMsgContent(),  chatRecord.getMsgType(),chatRecord.getMsgDate(), true,false, false);
                            }
                        } );

                    }
                    else if(talk.getTalkType().equals(TalkType.GROUP_MESSAGE.getTalkTypeCode())){
                        // 如果为空，则直接返回
                        if(chatRecordList == null || chatRecordList.isEmpty())
                            return;

                        chatRecordList.forEach(chatRecord ->{
                            // 如果是自己发送的消息
                            if(!chatRecord.getMsgUserType().equals(MsgUserType.OTHERS_MSG.getMsgTypeCode())){
                                chatController.addTalkMsgRight(chatRecord.getTalkId(), chatRecord.getMsgContent(),  chatRecord.getMsgType(),chatRecord.getMsgDate(), true,false, false);
                            }else {
                                chatController.addTalkMsgGroupLeft(chatRecord.getTalkId(), talk.getTalkName(), talk.getTalkHead(), chatRecord.getUserId(), chatRecord.getUserNickName(), chatRecord.getUserHead(), chatRecord.getMsgContent(),chatRecord.getMsgType(),chatRecord.getMsgDate(),true,false, false);
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

            // 显示好友申请消息
            chatController.fillNewFriendRequest();

            // TODO:显示群组申请消息
            chatController.fillGroupRequest();
        });

    }

    private List<TalkBoxData> getTalkList(String userId) {
        FileDataSource fileDataSource = new FileDataSource(userId);
        return fileDataSource.getTalkBoxList();
    }

    Map<String, List<ChatRecordDto>> getChatRecordMap(String userId){
        FileDataSource fileDataSource = new FileDataSource(userId);
        List<ChatRecordDto> chatRecordDtoList = fileDataSource.getChatRecordDtoList();

        Map<String, List<ChatRecordDto>> chatRecordMap = new HashMap<>();
        String talkId = "";
        if(chatRecordDtoList != null){
            for(ChatRecordDto chatRecordDto : chatRecordDtoList){

                talkId = chatRecordDto.getTalkId();
                List<ChatRecordDto> chatRecordDtos = chatRecordMap.get(talkId);
                if(chatRecordDtos == null){
                    chatRecordDtos = new ArrayList<>();
                    chatRecordMap.put(talkId, chatRecordDtos);
                }
                chatRecordDtos.add(chatRecordDto);
            }
        }
        ChatRecordMap.setChatRecordDtoMap(chatRecordMap);
        return  chatRecordMap;
    }
}