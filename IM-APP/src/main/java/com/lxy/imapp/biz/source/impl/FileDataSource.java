package com.lxy.imapp.biz.source.impl;

import com.lxy.imapp.biz.socket.po.NewFriendRequest;
import com.lxy.imapp.biz.socket.po.UserGroupRequest;
import com.lxy.imapp.biz.source.DataSource;
import com.lxy.imapp.biz.source.io.ObjectInputStreamWithNoHeader;
import com.lxy.imapp.biz.source.io.ObjectOutputStreamWithNoHeader;
import com.lxy.imapp.front.data.TalkBoxData;

import com.lxy.protocolpackage.dto.ChatRecordDto;
import com.lxy.protocolpackage.dto.ChatTalkDto;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FileDataSource implements DataSource {

    private String filePrefix = "src/main/resources/";

    private String fileDir;

    private String chatRecordFileName = "chat_record";

    private String talkFileName = "chat_talk";

    private String newFriendRequestFileName = "new_friend_request";

    private String newJoinGroupRequestFileName = "new_join_group_request";

    private String groupRequestFileName = "group_request";


    public FileDataSource(String fileDir) {
        this.fileDir = fileDir;
        try {
            File fileDirObj = new File(filePrefix + fileDir);
            if (!fileDirObj.exists())
                fileDirObj.mkdir();

            // 创建消息本地缓存
            chatRecordFileName = filePrefix + fileDir + "/" + chatRecordFileName;
            File fileObj = new File(chatRecordFileName);
            if (!fileObj.exists()) {
                fileObj.createNewFile();
            }

            // 创建本地对话缓存
            talkFileName = filePrefix + fileDir + "/" + talkFileName;
            File fileTalkObj = new File(talkFileName);
            if (!fileTalkObj.exists()) {
                fileTalkObj.createNewFile();
            }

            // 创建好友申请消息缓存
            newFriendRequestFileName = filePrefix + fileDir + "/" + newFriendRequestFileName;
            File fileNewFriendRequestObj = new File(newFriendRequestFileName);
            if (!fileNewFriendRequestObj.exists()) {
                fileNewFriendRequestObj.createNewFile();
            }

            // 创建群组申请消息缓存
            groupRequestFileName = filePrefix + fileDir + "/" + groupRequestFileName;
            File fileGroupRequestObj = new File(groupRequestFileName);
            if (!fileGroupRequestObj.exists()) {
                fileGroupRequestObj.createNewFile();
            }

        }catch (Exception e){
            log.error(e.getMessage());
        }
    }


    @Override
    public void addTalkBox(TalkBoxData talkBoxData) {
        File fileObj = new File(talkFileName);

        try (ObjectOutputStreamWithNoHeader oos = new ObjectOutputStreamWithNoHeader(new FileOutputStream(fileObj))) {
            oos.writeObject(talkBoxData);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // 覆盖写
    @Override
    public void addTalkBoxList(List<TalkBoxData> talkBoxDataList) {
        File fileObj = new File(talkFileName);

        try (ObjectOutputStreamWithNoHeader oos = new ObjectOutputStreamWithNoHeader(new FileOutputStream(fileObj))) {
            for(TalkBoxData talkBoxData : talkBoxDataList)
                oos.writeObject(talkBoxData);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public List<TalkBoxData> getTalkBoxList() {
        List<TalkBoxData> objects = new ArrayList<>();

        File file = new File(talkFileName);

        try (ObjectInputStreamWithNoHeader ois = new ObjectInputStreamWithNoHeader(new FileInputStream(file))) {
            while (true) {
                try {
                    TalkBoxData obj = (TalkBoxData) ois.readObject();
                    objects.add(obj);
                }catch (EOFException e){
                    break;
                }
            }
        }catch (Exception e){
            log.error(e.getStackTrace().toString());
            return objects;
        }

        return objects;
    }

    // 因为消息不允许修改，所以为追加写
    @Override
    public void addChatRecordDto(ChatRecordDto chatRecordDto) {
        File fileObj = new File(chatRecordFileName);

        try (ObjectOutputStreamWithNoHeader oos = new ObjectOutputStreamWithNoHeader(new FileOutputStream(fileObj, true))) {
            oos.writeObject(chatRecordDto);
            oos.flush();
        }catch (Exception e){
            System.out.println("出现异常");
           log.error(e.getMessage());
        }


    }

    @Override
    public void addChatRecordDto(List<ChatRecordDto> chatRecordDtoList) {
        File fileObj = new File(chatRecordFileName);

        try (ObjectOutputStreamWithNoHeader oos = new ObjectOutputStreamWithNoHeader(new FileOutputStream(fileObj))) {
            for(ChatRecordDto chatRecordDto : chatRecordDtoList){
                oos.writeObject(chatRecordDto);
            }
            oos.flush();
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public List<ChatRecordDto> getChatRecordDtoList() {
        List<ChatRecordDto> objects = new ArrayList<>();
        File file = new File(chatRecordFileName);

        try (ObjectInputStreamWithNoHeader ois = new ObjectInputStreamWithNoHeader(new FileInputStream(file))) {
            while (true) {
                try {
                    ChatRecordDto obj = (ChatRecordDto) ois.readObject();
                    objects.add(obj);
                }catch (EOFException e){
                    break;
                }
            }
        }catch (Exception e){
            log.error(e.getStackTrace().toString());
            return objects;
        }

        return objects;
    }

    // 覆盖写
    @Override
    public void addNewFriendRequestList(List<NewFriendRequest> newFriendRequestList) {
        File fileObj = new File(newFriendRequestFileName);

        try (ObjectOutputStreamWithNoHeader oos = new ObjectOutputStreamWithNoHeader(new FileOutputStream(fileObj))) {
            for(NewFriendRequest newFriendRequest : newFriendRequestList){
                oos.writeObject(newFriendRequest);
            }
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public List<NewFriendRequest> getNewFriendRequestList() {
        List<NewFriendRequest> objects = new ArrayList<>();
        File file = new File(newFriendRequestFileName);

        try (ObjectInputStreamWithNoHeader ois = new ObjectInputStreamWithNoHeader(new FileInputStream(file))) {
            while (true) {
                try {
                    NewFriendRequest obj = (NewFriendRequest) ois.readObject();
                    objects.add(obj);
                }catch (EOFException e){
                    break;
                }
            }
        }catch (Exception e){
            log.error(e.getStackTrace().toString());
            return objects;
        }

        return objects;
    }

    @Override
    public void addGroupRequestList(List<UserGroupRequest> userGroupRequests) {
        File fileObj = new File(groupRequestFileName);

        try (ObjectOutputStreamWithNoHeader oos = new ObjectOutputStreamWithNoHeader(new FileOutputStream(fileObj))) {
            for(UserGroupRequest groupRequest : userGroupRequests){
                oos.writeObject(groupRequest);
            }
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public List<UserGroupRequest> getGroupRequestList() {
        List<UserGroupRequest> objects = new ArrayList<>();
        File file = new File(groupRequestFileName);

        try (ObjectInputStreamWithNoHeader ois = new ObjectInputStreamWithNoHeader(new FileInputStream(file))) {
            while (true) {
                try {
                    UserGroupRequest obj = (UserGroupRequest) ois.readObject();
                    objects.add(obj);
                }catch (EOFException e){
                    break;
                }
            }
        }catch (Exception e){
            log.error(e.getStackTrace().toString());
            return objects;
        }

        return objects;
    }
}
