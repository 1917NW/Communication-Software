package com.lxy.protocolpackage.mq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class MessageTag {
    // 私聊
   public static final String CHAT_TAG = "chat";

   // 群聊
   public static final String GROUP_CHAT_TAG = "group_chat";

   // 申请好友

    public static final String FRIEND_REQUEST = "friend_request";

    public static final String ADD_FRIEND_RESPONSE = "add_friend_response";


    // 申请群组
    public static final String GROUP_REQUEST = "group_request";

    public static final String FULL_GROUP_JOIN_In_GROUP_RESPONSE = "full_group_join_in_response";



}

