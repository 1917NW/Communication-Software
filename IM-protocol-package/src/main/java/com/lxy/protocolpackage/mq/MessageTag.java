package com.lxy.protocolpackage.mq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class MessageTag {
   public static final String CHAT_TAG = "chat";

   public static final String GROUP_CHAT_TAG = "group_chat";

    public static final String FRIEND_REQUEST = "friend_request";
    public static final String GROUP_REQUEST = "group_request";
}

