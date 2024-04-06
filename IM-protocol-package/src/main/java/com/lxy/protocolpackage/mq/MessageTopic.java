package com.lxy.protocolpackage.mq;

public class MessageTopic {
    public static final String IM_TOPIC = "im-server";

    public static String getImTopicWithTag(String tag){
        return IM_TOPIC + ":" + tag;
    }

    public static String getChatTag(){
        return getImTopicWithTag(MessageTag.CHAT_TAG);
    }

    public static String getFriendRequestTag(){
        return getImTopicWithTag(MessageTag.FRIEND_REQUEST);
    }
    public static String getGroupRequestTag(){
        return getImTopicWithTag(MessageTag.GROUP_REQUEST);
    }

    public static String getGroupChatTag(){
        return getImTopicWithTag(MessageTag.GROUP_CHAT_TAG);
    }
}
