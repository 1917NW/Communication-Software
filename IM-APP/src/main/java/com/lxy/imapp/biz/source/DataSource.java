package com.lxy.imapp.biz.source;

import com.lxy.imapp.biz.socket.po.NewFriendRequest;
import com.lxy.imapp.biz.socket.po.UserGroupRequest;
import com.lxy.imapp.front.data.TalkBoxData;
import com.lxy.protocolpackage.dto.ChatRecordDto;


import java.util.List;

public interface DataSource {
    // 添加对话框
    public void addTalkBox(TalkBoxData talkBoxData);

    // 添加多个对话框
    public void addTalkBoxList(List<TalkBoxData> talkBoxDataList);

    // 获取多个对话框
    public List<TalkBoxData> getTalkBoxList();

    // 写入单条消息
    public void addChatRecordDto(ChatRecordDto chatRecordDto);

    // 写入多条消息
    public void addChatRecordDto(List<ChatRecordDto> chatTalkDtoList);

    // 读取多条消息
    public List<ChatRecordDto> getChatRecordDtoList();


    // TODO:通知消息

    // 好友申请通知消息
    // 写入多条消息
    public void addNewFriendRequestList(List<NewFriendRequest> newFriendRequestList);


    // 获取好友申请通知消息
    public List<NewFriendRequest> getNewFriendRequestList();

    // 申请加入群组消息
    public void addGroupRequestList(List<UserGroupRequest> userGroupRequests);

    // 获取群组消息
    public List<UserGroupRequest> getGroupRequestList();
}
