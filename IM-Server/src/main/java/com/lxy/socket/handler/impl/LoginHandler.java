package com.lxy.socket.handler.impl;

import com.lxy.application.UserService;
import com.lxy.domain.user.model.*;
import com.lxy.infrastructure.common.SocketChannelUtil;
import com.lxy.protocolpackage.constants.MsgType;
import com.lxy.protocolpackage.constants.TalkType;
import com.lxy.protocolpackage.protocol.login.LoginRequest;
import com.lxy.protocolpackage.protocol.login.LoginResponse;
import com.lxy.protocolpackage.protocol.login.dto.ChatRecordDto;
import com.lxy.protocolpackage.protocol.login.dto.ChatTalkDto;
import com.lxy.protocolpackage.protocol.login.dto.GroupsDto;
import com.lxy.protocolpackage.protocol.login.dto.UserFriendDto;
import com.lxy.socket.handler.AbstractBizHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ChannelHandler.Sharable
public class LoginHandler extends AbstractBizHandler<LoginRequest> {

    @Autowired
    private UserService userService;

    @Override
    public void channelRead(Channel channel, LoginRequest msg) {
        System.out.println("用户id:" + msg.getUserId() + " 用户密码:"+msg.getUserPassword());
        LoginResponse loginResponse = new LoginResponse();

        // 校验账号密码
        String userId = msg.getUserId();
        String userPassword = msg.getUserPassword();
        boolean checkLoginResult = userService.checkAuth(userId, userPassword);
        if(!checkLoginResult) {
            loginResponse.setSuccess(false);
            channel.writeAndFlush(loginResponse);
            return;
        }

        SocketChannelUtil.addChannel(msg.getUserId(), channel);

        // 校验成功则返回用户个人信息
        loginResponse.setSuccess(true);
        UserInfo userInfo = userService.queryUserInfo(userId);
        System.out.println("用户登录成功:"+userInfo);
        loginResponse.setUserId(userInfo.getUserId());
        loginResponse.setUserNickName(userInfo.getUserNickname());
        loginResponse.setUserHead(userInfo.getUserHead());

        // 3.2 查询对话信息
        List<TalkBoxInfo> talkBoxInfoList = userService.queryTalkBoxInfoList(userId);

        if(talkBoxInfoList!=null) {
            for (TalkBoxInfo talkBoxInfo : talkBoxInfoList) {
                ChatTalkDto chatTalkDto = new ChatTalkDto();

                chatTalkDto.setTalkId(talkBoxInfo.getTalkId());
                chatTalkDto.setTalkType(talkBoxInfo.getTalkType());
                chatTalkDto.setTalkName(talkBoxInfo.getTalkName());
                chatTalkDto.setTalkHead(talkBoxInfo.getTalkHead());
                chatTalkDto.setTalkSketch(talkBoxInfo.getTalkSketch());
                chatTalkDto.setTalkDate(talkBoxInfo.getTalkDate());
                loginResponse.getChatTalkList().add(chatTalkDto);

                // 好友；聊天消息
                if (TalkType.PRIVATE_MESSAGE.getTalkTypeCode().equals(talkBoxInfo.getTalkType())) {
                    List<ChatRecordDto> chatRecordDtoList = new ArrayList<>();

                    List<ChatRecordInfo> chatRecordInfoList = userService.queryChatRecordInfoList(talkBoxInfo.getTalkId(), msg.getUserId(), TalkType.PRIVATE_MESSAGE.getTalkTypeCode());

                    for (ChatRecordInfo chatRecordInfo : chatRecordInfoList) {
                        ChatRecordDto chatRecordDto = new ChatRecordDto();
                        chatRecordDto.setTalkId(talkBoxInfo.getTalkId());
                        boolean msgType = msg.getUserId().equals(chatRecordInfo.getUserId());
                        // 自己发的消息
                        if (msgType) {
                            chatRecordDto.setUserId(chatRecordInfo.getUserId());
                            chatRecordDto.setMsgType(MsgType.MINE_MSG.getMsgTypeCode()); // 消息类型[0自己/1好友]
                        }
                        // 好友发的消息
                        else {
                            chatRecordDto.setUserId(chatRecordInfo.getUserId());
                            chatRecordDto.setMsgType(MsgType.OTHERS_MSG.getMsgTypeCode()); // 消息类型[0自己/1好友]
                        }
                        chatRecordDto.setMsgContent(chatRecordInfo.getMsgContent());
                        chatRecordDto.setMsgDate(chatRecordInfo.getMsgDate());
                        chatRecordDtoList.add(chatRecordDto);
                    }
                    chatTalkDto.setChatRecordList(chatRecordDtoList);
                }
                // 群组；聊天消息
                else if (TalkType.GROUP_MESSAGE.getTalkTypeCode().equals(talkBoxInfo.getTalkType())) {
                    List<ChatRecordDto> chatRecordDtoList = new ArrayList<>();
                    List<ChatRecordInfo> chatRecordInfoList = userService.queryChatRecordInfoList(talkBoxInfo.getTalkId(), msg.getUserId(), TalkType.GROUP_MESSAGE.getTalkTypeCode());
                    for (ChatRecordInfo chatRecordInfo : chatRecordInfoList) {
                        UserInfo memberInfo = userService.queryUserInfo(chatRecordInfo.getUserId());
                        ChatRecordDto chatRecordDto = new ChatRecordDto();
                        chatRecordDto.setTalkId(talkBoxInfo.getTalkId());
                        chatRecordDto.setUserId(memberInfo.getUserId());
                        chatRecordDto.setUserNickName(memberInfo.getUserNickname());
                        chatRecordDto.setUserHead(memberInfo.getUserHead());
                        chatRecordDto.setMsgContent(chatRecordInfo.getMsgContent());
                        chatRecordDto.setMsgDate(chatRecordInfo.getMsgDate());

                        boolean msgType = msg.getUserId().equals(chatRecordInfo.getUserId());
                        // 如果是自己的消息
                        if(msgType){
                            chatRecordDto.setMsgType(MsgType.MINE_MSG.getMsgTypeCode());
                        }else {
                            chatRecordDto.setMsgType(MsgType.OTHERS_MSG.getMsgTypeCode());
                        }
                        chatRecordDtoList.add(chatRecordDto);
                    }
                    chatTalkDto.setChatRecordList(chatRecordDtoList);
                }
            }
        }

        // 3.3 查询群组信息
        List<GroupsInfo> groupsInfoList = userService.queryUserGroupInfoList(userId);
        if(groupsInfoList != null) {
            for (GroupsInfo groupsInfo : groupsInfoList) {
                GroupsDto groups = new GroupsDto();
                groups.setGroupId(groupsInfo.getGroupId());
                groups.setGroupName(groupsInfo.getGroupName());
                groups.setGroupHead(groupsInfo.getGroupHead());
                loginResponse.getGroupsList().add(groups);
            }
        }

        // 3.4 查询好友信息
        List<UserFriendInfo> userFriendInfoList = userService.queryUserFriendInfoList(userId);
        if(userFriendInfoList != null) {
            for (UserFriendInfo userFriendInfo : userFriendInfoList) {
                UserFriendDto userFriend = new UserFriendDto();
                userFriend.setFriendId(userFriendInfo.getFriendId());
                userFriend.setFriendName(userFriendInfo.getFriendName());
                userFriend.setFriendHead(userFriendInfo.getFriendHead());
                loginResponse.getUserFriendList().add(userFriend);
            }
        }

        channel.writeAndFlush(loginResponse);
    }
}