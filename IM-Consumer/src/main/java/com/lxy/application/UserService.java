package com.lxy.application;


import com.lxy.domain.user.model.*;
import com.lxy.infrastructure.entity.ImUserFriend;
import com.lxy.protocolpackage.dto.GroupDto;

import java.util.List;

public interface UserService {

    /**
     * 登陆校验
     *
     * @param userId       用户ID
     * @param userPassword 用户密码
     * @return 登陆状态
     */
    boolean checkAuth(String userId, String userPassword);

    /**
     * 批量插入对话框
     * @param userIdList
     * @param groupId
     * @param talkType
     */
    void addTalkBoxInfoBatch(List<String> userIdList, String groupId, Integer talkType);

    List<String> getUserIdListByGroupId(String groupId);
    /**
     * 查询用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    UserInfo queryUserInfo(String userId);

    /**
     * 查询个人用户对话框列表
     *
     * @param userId 个人用户ID
     * @return 对话框列表
     */
    List<TalkBoxInfo> queryTalkBoxInfoList(String userId);

    boolean createGroup(GroupsInfo groupsInfo);

    /**
     * 添加对话框
     *
     * @param userId   用户ID
     * @param talkId   好友ID
     * @param talkType 对话框类型[0好友、1群组]
     */
    void addTalkBoxInfo(String userId, String talkId, Integer talkType);

    /**
     * 查询个人用户好友列表
     *
     * @param userId 个人用户ID
     * @return 好友列表
     */
    List<UserFriendInfo> queryUserFriendInfoList(String userId);


    /**
     * 查询个人用户群组列表
     *
     * @param userId 个人用户ID
     * @return 群组列表
     */
    List<GroupsInfo> queryUserGroupInfoList(String userId);

    /**
     * 模糊查询用户
     *
     * @param userId    用户ID
     * @param searchKey 用户名、用户ID
     * @return < 10个用户集合
     */
    List<LuckUserInfo> queryFuzzyUserInfoList(String userId, String searchKey);




    List<GroupDto> queryFuzzyGroupList(String userId, String searchKey);

    /**
     * 添加好友到数据库中
     *
     * @param userFriendList 好友集合
     */
    void addUserFriend(List<ImUserFriend> userFriendList);

    /**
     * 异步添加聊天记录
     *
     * @param chatRecordInfo 聊天记录信息
     */
    void asyncAppendChatRecord(ChatRecordInfo chatRecordInfo);

    /**
     * 查询聊天记录
     *
     * @param talkId   对话框ID
     * @param userId   好友ID
     * @param talkType 对话类型；0好友、1群组
     * @return 聊天记录(10条)
     */
    List<ChatRecordInfo> queryChatRecordInfoList(String talkId, String userId, Integer talkType);

    /**
     * 删除用户对话框
     *
     * @param userId 用户ID
     * @param talkId 对话框ID
     */
    void deleteUserTalk(String userId, String talkId, Integer talkType);

    /**
     * 查询用户群组ID集合
     *
     * @param userId 用户ID
     * @return 用户群组ID集合
     */
    List<String> queryUserGroupsIdList(String userId);

    /**
     * 查询用户群组对话框
     *
     * @param userId 用户Id
     * @return       群组Id
     */
    List<String> queryTalkBoxGroupsIdList(String userId);

    /**
     * 异步插入添加好友信息
     * @param userId
     * @param friendId
     */

    void asyncAddUserFriend(String userId, String friendId);

    void asyncDeleteUserFriend(String userId, String friendId);

    GroupDto queryGroupInfo(String groupId);

    boolean addUserToGroup(String userId, String groupId);
}
