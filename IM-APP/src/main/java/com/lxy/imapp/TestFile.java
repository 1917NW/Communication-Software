package com.lxy.imapp;

import com.lxy.imapp.biz.socket.po.NewFriendRequest;
import com.lxy.imapp.biz.source.impl.FileDataSource;
import com.lxy.imapp.biz.util.BeanUtil;
import com.lxy.imapp.front.data.TalkBoxData;
import com.lxy.protocolpackage.constants.MsgUserType;
import com.lxy.protocolpackage.dto.ChatRecordDto;


import java.util.ArrayList;
import java.util.List;

public class TestFile {
    public static void main(String[] args) {
        FileDataSource fileDataSource = new FileDataSource("1234");


        List<ChatRecordDto> chatRecordDtoList = fileDataSource.getChatRecordDtoList();

        for(ChatRecordDto chatRecordDto1 : chatRecordDtoList){
            System.out.println(chatRecordDto1);
        }

    }
}
