package com.lxy.socket.handler.impl;

import cn.hutool.json.JSONUtil;
import com.lxy.application.OfflineMsgService;
import com.lxy.application.UserService;
import com.lxy.domain.user.model.GroupsInfo;
import com.lxy.domain.user.model.UserFriendInfo;
import com.lxy.domain.user.model.UserInfo;
import com.lxy.infrastructure.common.SocketChannelUtil;
import com.lxy.infrastructure.common.UserOffineMsgCache;
import com.lxy.infrastructure.entity.ImOfflineMsg;
import com.lxy.protocolpackage.dto.GroupsDto;
import com.lxy.protocolpackage.dto.UserFriendDto;
import com.lxy.protocolpackage.protocol.Packet;
import com.lxy.protocolpackage.protocol.login.LoginRequest;
import com.lxy.protocolpackage.protocol.login.LoginResponse;
import com.lxy.socket.NettyServer;
import com.lxy.socket.handler.AbstractBizHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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

        // 保存个人Channel
        SocketChannelUtil.addChannel(msg.getUserId(), channel);


        // 保存群组Channel
        List<String> groupIdList = userService.queryUserGroupsIdList(msg.getUserId());
        for(String groupId : groupIdList){
            SocketChannelUtil.addChannelGroup(groupId, channel);
        }


        // 校验成功则返回用户个人信息
        loginResponse.setSuccess(true);
        UserInfo userInfo = userService.queryUserInfo(userId);
        System.out.println("用户登录成功:"+userInfo);
        loginResponse.setUserId(userInfo.getUserId());
        loginResponse.setUserNickName(userInfo.getUserNickname());
        loginResponse.setUserHead(userInfo.getUserHead());



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

        // 发送离线时的消息
        clearOfflineMsg(channel, userId);
    }

    @Autowired
    private OfflineMsgService offlineMsgService;


    private void clearOfflineMsg(Channel channel, String userId) {

        // 先发送之前保存在数据库中的离线消息
        List<ImOfflineMsg> offlineMsgLocalList = offlineMsgService.getOfflineMsgByServerIdAndUserId(NettyServer.serverId, userId);
        List<Packet> collect = offlineMsgLocalList.stream().map(offlineMsg -> {
            return JSONUtil.toBean(offlineMsg.getPacketJsonStr(), Packet.get(offlineMsg.getPacketType()));
        }).collect(Collectors.toList());
        if(collect != null && !collect.isEmpty()){
            for(Packet packet : collect){
                channel.writeAndFlush(packet);
            }
        }

        // 再发送保存到缓存中的离线消息
        List<Packet> offlineMsgList = UserOffineMsgCache.getOfflineMsgByUserId(userId);
        System.out.println("清空离线时的消息:" + JSONUtil.toJsonStr(offlineMsgList));
        if(offlineMsgList != null && !offlineMsgList.isEmpty()){
            Iterator<Packet> iterator = offlineMsgList.iterator();
            while (iterator.hasNext()){
                Packet next = iterator.next();
                channel.writeAndFlush(next);
                iterator.remove();
            }

        }


    }
}