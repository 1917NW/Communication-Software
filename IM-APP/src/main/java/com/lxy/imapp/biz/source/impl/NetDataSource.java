package com.lxy.imapp.biz.source.impl;

import com.lxy.imapp.biz.socket.po.NewFriendRequest;
import com.lxy.imapp.biz.socket.po.UserGroupRequest;
import com.lxy.imapp.biz.source.DataSource;
import com.lxy.imapp.front.data.TalkBoxData;

import com.lxy.protocolpackage.dto.ChatRecordDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NetDataSource implements DataSource {

    // 对话框文件
    private String talkFileName;

    // 消息文件
    private String chatRecordFileName;

    // 通知消息文件
    private String friendRequestFileName;

    private String groupRequestFileName;


    @Override
    public void addTalkBox(TalkBoxData talkBoxData) {

    }

    @Override
    public void addTalkBoxList(List<TalkBoxData> talkBoxDataList) {

    }

    @Override
    public List<TalkBoxData> getTalkBoxList() {
        return null;
    }

    @Override
    public void addChatRecordDto(ChatRecordDto chatRecordDto) {

    }

    @Override
    public void addChatRecordDto(List<ChatRecordDto> chatTalkDtoList) {

    }

    @Override
    public List<ChatRecordDto> getChatRecordDtoList() {
        return  null;
    }

    @Override
    public void addNewFriendRequestList(List<NewFriendRequest> newFriendRequestList) {

    }

    @Override
    public List<NewFriendRequest> getNewFriendRequestList() {
        return null;
    }

    @Override
    public void addGroupRequestList(List<UserGroupRequest> userGroupRequests) {

    }

    @Override
    public List<UserGroupRequest> getGroupRequestList() {
        return null;
    }
}
