package com.lxy.protocolpackage.protocol;

import com.lxy.protocolpackage.protocol.friend.*;
import com.lxy.protocolpackage.protocol.group.*;
import com.lxy.protocolpackage.protocol.login.LoginRequest;
import com.lxy.protocolpackage.protocol.login.LoginResponse;
import com.lxy.protocolpackage.protocol.msg.MsgRequest;
import com.lxy.protocolpackage.protocol.msg.MsgResponse;
import com.lxy.protocolpackage.protocol.talk.DelTalkRequest;
import com.lxy.protocolpackage.protocol.talk.TalkNoticeRequest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Packet {

    private final static Map<Byte, Class<? extends Packet>> packetType = new ConcurrentHashMap<>();

    static {
        // 登录消息
        packetType.put(Command.LoginRequest, LoginRequest.class);
        packetType.put(Command.LoginResponse, LoginResponse.class);

        // 搜索消息
        packetType.put(Command.SearchFriendRequest, SearchFriendRequest.class);
        packetType.put(Command.SearchFriendResponse, SearchFriendResponse.class);

        // 添加好友消息
        packetType.put(Command.AddFriendRequest, AddFriendRequest.class);
        packetType.put(Command.AddFriendResponse, AddFriendResponse.class);

        // 申请好友消息
        packetType.put(Command.FriendRequest, FriendRequest.class);
        packetType.put(Command.FriendResponse, FriendResponse.class);

        // 添加对话框消息
        packetType.put(Command.TalkNoticeRequest, TalkNoticeRequest.class);

        // 删除对话框消息
        packetType.put(Command.DelTalkRequest, DelTalkRequest.class);

        // 好友消息
        packetType.put(Command.MsgRequest, MsgRequest.class);
        packetType.put(Command.MsgResponse, MsgResponse.class);

        // 搜索群组消息
        packetType.put(Command.SearchGroupRequest, GroupSearchRequest.class);
        packetType.put(Command.SearchGroupResponse, GroupSearchResponse.class);

        // 创建群组消息
        packetType.put(Command.CreateGroupRequest, GroupCreateRequest.class);
        packetType.put(Command.CreateGroupResponse, GroupCreateResponse.class);

        // 群组申请添加消息
        packetType.put(Command.JoinInGroupRequest, JoinInGroupRequest.class);
        packetType.put(Command.JoinInGroupResponse, JoinInGroupResponse.class);

        packetType.put(Command.FullUserJoinInGroupRequest, FullUserJoinInGroupRequest.class);
        packetType.put(Command.FullGroupJoinInGroupResponse, FullGroupJoinInGroupResponse.class);

    }

    public static Class<? extends Packet> get(Byte command) {
        return packetType.get(command);
    }

    /**
     * 获取协议指令
     *
     * @return 返回指令值
     */
    public abstract Byte getCommand();

}