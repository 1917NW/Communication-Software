package com.lxy.protocolpackage.mq;

public class MessageTopic {
    public static final String IM_TOPIC = "im-server";

    public static String getImTopicWithTag(String tag){
        return IM_TOPIC + ":" + tag;
    }

    /**
     * 私聊tag
     * @return
     */
    public static String getChatTag(){
        return getImTopicWithTag(MessageTag.CHAT_TAG);
    }

    /**
     * 群聊tag
     * @return
     */
    public static String getGroupChatTag(){
        return getImTopicWithTag(MessageTag.GROUP_CHAT_TAG);
    }



    /**
     * 好友申请tag
     * @return
     */
    public static String getFriendRequestTag(){
        return getImTopicWithTag(MessageTag.FRIEND_REQUEST);
    }

    public static String getAddFriendResponseTag(){
        return getImTopicWithTag(MessageTag.ADD_FRIEND_RESPONSE);
    }


    /**
     * 群组申请tag
     * @return
     */
    public static String getGroupRequestTag(){
        return getImTopicWithTag(MessageTag.GROUP_REQUEST);
    }

    public static String getFullGroupJoinInGroupResponseTag(){
        return getImTopicWithTag(MessageTag.FULL_GROUP_JOIN_In_GROUP_RESPONSE);
    }


}
