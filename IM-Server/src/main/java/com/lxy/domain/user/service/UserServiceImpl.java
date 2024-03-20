package com.lxy.domain.user.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lxy.application.UserService;
import com.lxy.domain.user.model.*;
import com.lxy.infrastructure.dao.*;
import com.lxy.infrastructure.entity.*;
import com.lxy.protocolpackage.constants.TalkType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ImUserDao imUserDao;

    @Override
    public boolean checkAuth(String userId, String userPassword) {
        if(!StringUtils.hasLength(userId)){
            return false;
        }

        if(!StringUtils.hasLength(userPassword))
            return false;

        LambdaQueryWrapper<ImUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ImUser::getUserId, userId);
        lambdaQueryWrapper.select(ImUser::getUserPassword);
        ImUser imUser = imUserDao.selectOne(lambdaQueryWrapper);

        if(!userPassword.equals(imUser.getUserPassword()))
            return false;

        return true;
    }

    @Override
    public UserInfo queryUserInfo(String userId) {
        LambdaQueryWrapper<ImUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ImUser::getUserId, userId);
        ImUser imUser = imUserDao.selectOne(lambdaQueryWrapper);
        return BeanUtil.copyProperties(imUser, UserInfo.class);

    }


    @Autowired
    private ImUserTalkDao imUserTalkDao;

    @Autowired
    private ImGroupDao imGroupDao;

    @Override
    public List<TalkBoxInfo> queryTalkBoxInfoList(String userId) {
        List<TalkBoxInfo> talkBoxInfoList = new ArrayList<>();
        LambdaQueryWrapper<ImUserTalk> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ImUserTalk::getUserId, userId);
        List<ImUserTalk> imUserTalks = imUserTalkDao.selectList(lambdaQueryWrapper);
        // TODO:SQL查询次数太多
        if(imUserTalks != null){
            for(ImUserTalk imUserTalk : imUserTalks) {
                TalkBoxInfo talkBoxInfo = new TalkBoxInfo();
                if(TalkType.PRIVATE_MESSAGE.getTalkTypeCode().equals(imUserTalk.getTalkType())){
                    LambdaQueryWrapper<ImUser> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    userLambdaQueryWrapper.eq(ImUser::getUserId, imUserTalk.getTalkId());
                    ImUser imUser = imUserDao.selectOne(userLambdaQueryWrapper);
                    talkBoxInfo.setTalkId(imUser.getUserId());
                    talkBoxInfo.setTalkType(TalkType.PRIVATE_MESSAGE.getTalkTypeCode());
                    talkBoxInfo.setTalkName(imUser.getUserNickname());
                    talkBoxInfo.setTalkHead(imUser.getUserHead());
                }else if(TalkType.GROUP_MESSAGE.getTalkTypeCode().equals(imUserTalk.getTalkType())){
                    LambdaQueryWrapper<ImGroup> groupLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    groupLambdaQueryWrapper.eq(ImGroup::getGroupId, imUserTalk.getTalkId());
                    ImGroup imGroup = imGroupDao.selectOne(groupLambdaQueryWrapper);
                    talkBoxInfo.setTalkId(imGroup.getGroupId());
                    talkBoxInfo.setTalkType(TalkType.GROUP_MESSAGE.getTalkTypeCode());
                    talkBoxInfo.setTalkName(imGroup.getGroupName());
                    talkBoxInfo.setTalkHead(imGroup.getGroupHead());
                }

                talkBoxInfoList.add(talkBoxInfo);
            }
       }

        return talkBoxInfoList;
    }

    @Override
    public void addTalkBoxInfo(String userId, String talkId, Integer talkType) {

    }

    @Autowired
    private ImUserFriendDao imUserFriendDao;

    @Override
    public List<UserFriendInfo> queryUserFriendInfoList(String userId) {
        List<UserFriendInfo> userFriendInfoList = new ArrayList<>();
        LambdaQueryWrapper<ImUserFriend> imUserFriendLambdaQueryWrapper = new LambdaQueryWrapper<>();
        imUserFriendLambdaQueryWrapper.eq(ImUserFriend::getUserId, userId);
        imUserFriendLambdaQueryWrapper.select(ImUserFriend::getUserFriendId);
        List<ImUserFriend> imUserFriends = imUserFriendDao.selectList(imUserFriendLambdaQueryWrapper);

        if(imUserFriends != null){
            for(ImUserFriend imUserFriend : imUserFriends){
                LambdaQueryWrapper<ImUser> imUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
                imUserLambdaQueryWrapper.eq(ImUser::getUserId, imUserFriend.getUserFriendId());
                ImUser imUser = imUserDao.selectOne(imUserLambdaQueryWrapper);
                UserFriendInfo friendInfo = new UserFriendInfo();
                friendInfo.setFriendId(imUser.getUserId());
                friendInfo.setFriendName(imUser.getUserNickname());
                friendInfo.setFriendHead(imUser.getUserHead());
                userFriendInfoList.add(friendInfo);
            }
        }

        return userFriendInfoList;
    }

    @Autowired
    private ImUserGroupDao imUserGroupDao;

    @Override
    public List<GroupsInfo> queryUserGroupInfoList(String userId) {
        List<GroupsInfo> groupsInfoList = new ArrayList<>();
        LambdaQueryWrapper<ImUserGroup> imUserGroupLambdaQueryWrapper = new LambdaQueryWrapper<>();
        imUserGroupLambdaQueryWrapper.eq(ImUserGroup::getUserId, userId);
        imUserGroupLambdaQueryWrapper.select(ImUserGroup::getGroupId);
        List<ImUserGroup> imUserGroups = imUserGroupDao.selectList(imUserGroupLambdaQueryWrapper);

        if(imUserGroups != null){
            for(ImUserGroup imUserGroup : imUserGroups){
                LambdaQueryWrapper<ImGroup> imGroupLambdaQueryWrapper = new LambdaQueryWrapper<>();
                imGroupLambdaQueryWrapper.eq(ImGroup::getGroupId, imUserGroup.getGroupId());
                ImGroup imGroup = imGroupDao.selectOne(imGroupLambdaQueryWrapper);
                GroupsInfo groupsInfo = new GroupsInfo();
                groupsInfo.setGroupId(imGroup.getGroupId());
                groupsInfo.setGroupName(imGroup.getGroupName());
                groupsInfo.setGroupHead(imGroup.getGroupHead());
                groupsInfoList.add(groupsInfo);

            }
        }

        return groupsInfoList;
    }

    @Override
    public List<LuckUserInfo> queryFuzzyUserInfoList(String userId, String searchKey) {
        return null;
    }

    @Override
    public void addUserFriend(List<ImUserFriend> userFriendList) {

    }

    @Override
    public void asyncAppendChatRecord(ChatRecordInfo chatRecordInfo) {

    }

    @Autowired
    private ImUserMsgDao imUserMsgDao;

    @Override
    public List<ChatRecordInfo> queryChatRecordInfoList(String talkId, String userId, Integer talkType) {
        List<ChatRecordInfo> chatRecordInfoList = new ArrayList<>();
        List<ImUserMsg> list = new ArrayList<>();
        if (TalkType.PRIVATE_MESSAGE.getTalkTypeCode().equals(talkType)){
            list = imUserMsgDao.queryFriendMsgByUserId(userId, talkId, TalkType.PRIVATE_MESSAGE.getTalkTypeCode());
        } else if (TalkType.GROUP_MESSAGE.getTalkTypeCode().equals(talkType)){
            list =  imUserMsgDao.queryGroupMsgByTalkId(talkId, TalkType.GROUP_MESSAGE.getTalkTypeCode());
        }
        for (ImUserMsg chatRecord : list) {
            ChatRecordInfo chatRecordInfo = new ChatRecordInfo();
            chatRecordInfo.setUserId(chatRecord.getUserId());
            chatRecordInfo.setFriendId(chatRecord.getTalkId());
            chatRecordInfo.setMsgContent(chatRecord.getMsgContent());
            chatRecordInfo.setMsgType(chatRecord.getTalktype());
            chatRecordInfo.setMsgDate(chatRecord.getMsgDate());
            chatRecordInfoList.add(chatRecordInfo);
        }
        return chatRecordInfoList;
    }

    @Override
    public void deleteUserTalk(String userId, String talkId) {

    }

    @Override
    public List<String> queryUserGroupsIdList(String userId) {
        return null;
    }

    @Override
    public List<String> queryTalkBoxGroupsIdList(String userId) {
        return null;
    }
}