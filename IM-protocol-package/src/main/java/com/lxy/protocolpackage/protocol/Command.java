package com.lxy.protocolpackage.protocol;

public interface Command {

    Byte LoginRequest = 1;
    Byte LoginResponse = 2;

    Byte MsgRequest = 3;
    Byte MsgResponse = 4;

    Byte TalkNoticeRequest = 5;
    Byte TalkNoticeResponse = 6;

    Byte SearchFriendRequest = 7;
    Byte SearchFriendResponse = 8;

    Byte AddFriendRequest = 9;
    Byte AddFriendResponse = 10;

    Byte DelTalkRequest = 11;

    Byte MsgGroupRequest = 12;
    Byte MsgGroupResponse = 13;

    Byte ReconnectRequest = 14;

    Byte FriendRequest = 15;
    Byte FriendResponse = 16;

    Byte SearchGroupRequest = 17;
    Byte SearchGroupResponse = 18;

    Byte CreateGroupRequest = 19;
    Byte CreateGroupResponse = 20;

    Byte JoinInGroupRequest = 21;

    Byte FullUserJoinInGroupRequest = 22;

    Byte FullGroupJoinInGroupResponse = 23;

    Byte JoinInGroupResponse = 24;

}