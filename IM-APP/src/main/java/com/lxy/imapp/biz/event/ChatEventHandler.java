package com.lxy.imapp.biz.event;

import com.alibaba.fastjson.JSON;
import com.lxy.imapp.biz.file.ChatRecordMap;
import com.lxy.imapp.biz.source.impl.FileDataSource;
import com.lxy.imapp.biz.threadPool.TaskExecutor;
import com.lxy.imapp.biz.util.BeanUtil;

import com.lxy.protocolpackage.constants.MsgType;
import com.lxy.protocolpackage.constants.MsgUserType;
import com.lxy.protocolpackage.constants.TalkType;
import com.lxy.protocolpackage.dto.ChatRecordDto;
import com.lxy.protocolpackage.dto.GroupDto;
import com.lxy.protocolpackage.dto.UserDto;
import com.lxy.protocolpackage.protocol.friend.*;

import com.lxy.protocolpackage.protocol.group.*;

import com.lxy.protocolpackage.protocol.msg.MsgRequest;
import com.lxy.protocolpackage.protocol.talk.DelTalkRequest;
import com.lxy.protocolpackage.protocol.talk.TalkNoticeRequest;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.Channel;

import java.util.Date;
import java.util.List;

public class ChatEventHandler {
    private Logger logger = LoggerFactory.getLogger(ChatEventHandler.class);
    
    public void doSearchNewFriend(String userId, List<Pane> listView){
        Channel channel = BeanUtil.getChannel();
        channel.writeAndFlush(new SearchFriendRequest(userId, ""));
    }

    public void doDeleteFriend(String deletedId){
        System.out.println("发送删除好友的消息：删除"+deletedId);
        Channel channel = BeanUtil.getChannel();
        channel.writeAndFlush(new DeleteFriendRequest(BeanUtil.getUserId(), deletedId));
    }



    public void doFriendLuckSearch(String userId, String text) {
        Channel channel = BeanUtil.getChannel();
        channel.writeAndFlush(new SearchFriendRequest(userId, text));
        System.out.println("发送搜索请求:"+text);
    }

    public void doGroupSearch(String userId, String text){
        Channel channel = BeanUtil.getChannel();
        channel.writeAndFlush(new GroupSearchRequest(userId, text));
    }

    public void doSendJoinInGroupRequest(String userId, String groupId, String groupLeaderId){
        System.out.println("userId:"+userId + "申请加入 groupId:"+groupId);
        Channel channel = BeanUtil.getChannel();
        channel.writeAndFlush(new JoinInGroupRequest(userId, groupId, groupLeaderId));
    }

    public void doAddNewUser(String userId, String friendId) {
        Channel channel = BeanUtil.getChannel();
    }

    public void doSendFriendRequest(String userId, String friendId){
        Channel channel = BeanUtil.getChannel();
        channel.writeAndFlush(new FriendRequest(userId, friendId));
        System.out.println("发送好友申请");
    }

    public void agreeFriendRequest(String userId, String requestFriendId){
        AddFriendResponse addFriendResponse = new AddFriendResponse();
        addFriendResponse.setAgree(true);
        addFriendResponse.setUserId(userId);
        addFriendResponse.setRequestFriendId(requestFriendId);

        System.out.println(JSON.toJSON(addFriendResponse));

        Channel channel = BeanUtil.getChannel();
        channel.writeAndFlush(addFriendResponse);
    }

    public void rejectFriendRequest(String userId, String requestFriendId){
        AddFriendResponse addFriendResponse = new AddFriendResponse();
        addFriendResponse.setAgree(false);
        addFriendResponse.setUserId(userId);
        addFriendResponse.setRequestFriendId(requestFriendId);

        Channel channel = BeanUtil.getChannel();
        channel.writeAndFlush(addFriendResponse);
    }

    public void doEventAddTalk(String userId, String talkId, Integer talkType) {
        Channel channel = BeanUtil.getChannel();
        System.out.println("确定对话信息："+ userId + " talk " + talkId);
        channel.writeAndFlush(new TalkNoticeRequest(userId, talkId, talkType));
    }

    public void doEventDeleteTalk(String userId, String talkId, Integer talkType){
        Channel channel = BeanUtil.getChannel();
        System.out.println("确定对话信息："+ userId + " talk " + talkId);
        channel.writeAndFlush(new DelTalkRequest(userId, talkId, talkType));
    }

    public void doEventSendMsg(String userId, String userNickname, String userHead, String talkId, Integer talkType, String msg, Integer msgType, Date msgDate){
        Channel channel = BeanUtil.getChannel();
        System.out.println("发送对话:");
        // TODO 表情
        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        userDto.setUserNickName(userNickname);
        userDto.setUserHead(userHead);
        if(TalkType.PRIVATE_MESSAGE.getTalkTypeCode().equals(talkType)) {
            System.out.println("发送好友消息");
            channel.writeAndFlush(new MsgRequest(userDto, talkId, msg, msgType, msgDate));
        }
        else if(TalkType.GROUP_MESSAGE.getTalkTypeCode().equals(talkType)){
            System.out.println("发送群组消息");
            channel.writeAndFlush(new MsgGroupRequest(userDto, talkId, msg, msgType, msgDate));
        }



        ChatRecordDto chatRecordDto = new ChatRecordDto();
        chatRecordDto.setMsgUserType(MsgUserType.MINE_MSG.getMsgTypeCode());

        chatRecordDto.setTalkId(talkId);

        chatRecordDto.setUserId(BeanUtil.getUserId());
        chatRecordDto.setUserNickName(BeanUtil.getUserNickName());
        chatRecordDto.setUserHead(BeanUtil.getUserHead());

        chatRecordDto.setMsgContent(msg);
        chatRecordDto.setMsgDate(msgDate);

        chatRecordDto.setMsgType(msgType);
        ChatRecordMap.appendChatRecordDto(chatRecordDto);



    }

    public void doEventCreateGroup(String currentUserId, String text) {
        Channel channel = BeanUtil.getChannel();
        System.out.println("创建群组:");
        GroupCreateRequest groupCreateRequest = new GroupCreateRequest();
        groupCreateRequest.setGroupLeaderId(currentUserId);
        groupCreateRequest.setGroupName(text);
        channel.writeAndFlush(groupCreateRequest);
    }

    public void agreeJoinInGroupRequest(String userId, GroupDto groupDto){
        System.out.println("同意"+userId+"加入群组"+groupDto.getGroupId());
        FullGroupJoinInGroupResponse groupJoinInGroupResponse = new FullGroupJoinInGroupResponse();
        groupJoinInGroupResponse.setUserId(userId);
        groupJoinInGroupResponse.setGroupDto(groupDto);
        groupJoinInGroupResponse.setAgree(true);
        Channel channel = BeanUtil.getChannel();
        channel.writeAndFlush(groupJoinInGroupResponse);
    }
    public void rejectJoinInGroupRequest(String userId, GroupDto groupDto){
        FullGroupJoinInGroupResponse groupJoinInGroupResponse = new FullGroupJoinInGroupResponse();
        groupJoinInGroupResponse.setAgree(false);
        Channel channel = BeanUtil.getChannel();
        channel.writeAndFlush(groupJoinInGroupResponse);
    }

    public void doExitGroup(String groupId) {
        Channel channel = BeanUtil.getChannel();
        channel.writeAndFlush(new ExitGroupRequest(BeanUtil.getUserId(), groupId));

    }
}
