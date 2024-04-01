# Details

Date : 2024-03-26 22:29:19

Directory c:\\Users\\26327\\Desktop\\毕业设计\\通讯软件\\IM-APP

Total : 83 files,  4656 codes, 345 comments, 1293 blanks, all 6294 lines

[Summary](results.md) / Details / [Diff Summary](diff.md) / [Diff Details](diff-details.md)

## Files
| filename | language | code | comment | blank | total |
| :--- | :--- | ---: | ---: | ---: | ---: |
| [.idea/encodings.xml](/.idea/encodings.xml) | XML | 7 | 0 | 0 | 7 |
| [.idea/misc.xml](/.idea/misc.xml) | XML | 14 | 0 | 0 | 14 |
| [.idea/uiDesigner.xml](/.idea/uiDesigner.xml) | XML | 124 | 0 | 0 | 124 |
| [.idea/vcs.xml](/.idea/vcs.xml) | XML | 6 | 0 | 0 | 6 |
| [.mvn/wrapper/maven-wrapper.properties](/.mvn/wrapper/maven-wrapper.properties) | Properties | 2 | 0 | 0 | 2 |
| [mvnw.cmd](/mvnw.cmd) | Batch | 102 | 51 | 36 | 189 |
| [pom.xml](/pom.xml) | XML | 85 | 1 | 7 | 93 |
| [src/main/java/com/lxy/imapp/IMApplication.java](/src/main/java/com/lxy/imapp/IMApplication.java) | Java | 42 | 3 | 11 | 56 |
| [src/main/java/com/lxy/imapp/Main.java](/src/main/java/com/lxy/imapp/Main.java) | Java | 7 | 0 | 3 | 10 |
| [src/main/java/com/lxy/imapp/TestApplication.java](/src/main/java/com/lxy/imapp/TestApplication.java) | Java | 12 | 0 | 3 | 15 |
| [src/main/java/com/lxy/imapp/biz/event/ChatEventHandler.java](/src/main/java/com/lxy/imapp/biz/event/ChatEventHandler.java) | Java | 116 | 1 | 23 | 140 |
| [src/main/java/com/lxy/imapp/biz/event/LoginEventHandler.java](/src/main/java/com/lxy/imapp/biz/event/LoginEventHandler.java) | Java | 16 | 0 | 8 | 24 |
| [src/main/java/com/lxy/imapp/biz/socket/IMClientChannelInitializer.java](/src/main/java/com/lxy/imapp/biz/socket/IMClientChannelInitializer.java) | Java | 29 | 0 | 5 | 34 |
| [src/main/java/com/lxy/imapp/biz/socket/NettyClient.java](/src/main/java/com/lxy/imapp/biz/socket/NettyClient.java) | Java | 58 | 0 | 14 | 72 |
| [src/main/java/com/lxy/imapp/biz/socket/handler/AbstractBizHandler.java](/src/main/java/com/lxy/imapp/biz/socket/handler/AbstractBizHandler.java) | Java | 23 | 0 | 12 | 35 |
| [src/main/java/com/lxy/imapp/biz/socket/handler/impl/AddFriendRequestHandler.java](/src/main/java/com/lxy/imapp/biz/socket/handler/impl/AddFriendRequestHandler.java) | Java | 20 | 0 | 6 | 26 |
| [src/main/java/com/lxy/imapp/biz/socket/handler/impl/FriendResponseHandler.java](/src/main/java/com/lxy/imapp/biz/socket/handler/impl/FriendResponseHandler.java) | Java | 20 | 0 | 9 | 29 |
| [src/main/java/com/lxy/imapp/biz/socket/handler/impl/FullUserJoinInGroupRequestHandler.java](/src/main/java/com/lxy/imapp/biz/socket/handler/impl/FullUserJoinInGroupRequestHandler.java) | Java | 21 | 0 | 3 | 24 |
| [src/main/java/com/lxy/imapp/biz/socket/handler/impl/GroupCreateResponseHandler.java](/src/main/java/com/lxy/imapp/biz/socket/handler/impl/GroupCreateResponseHandler.java) | Java | 20 | 0 | 5 | 25 |
| [src/main/java/com/lxy/imapp/biz/socket/handler/impl/GroupSearchResponseHandler.java](/src/main/java/com/lxy/imapp/biz/socket/handler/impl/GroupSearchResponseHandler.java) | Java | 27 | 0 | 6 | 33 |
| [src/main/java/com/lxy/imapp/biz/socket/handler/impl/JoinInGroupResponseHandler.java](/src/main/java/com/lxy/imapp/biz/socket/handler/impl/JoinInGroupResponseHandler.java) | Java | 22 | 0 | 5 | 27 |
| [src/main/java/com/lxy/imapp/biz/socket/handler/impl/LoginHandler.java](/src/main/java/com/lxy/imapp/biz/socket/handler/impl/LoginHandler.java) | Java | 83 | 7 | 25 | 115 |
| [src/main/java/com/lxy/imapp/biz/socket/handler/impl/MsgGroupResponseHandler.java](/src/main/java/com/lxy/imapp/biz/socket/handler/impl/MsgGroupResponseHandler.java) | Java | 21 | 0 | 4 | 25 |
| [src/main/java/com/lxy/imapp/biz/socket/handler/impl/MsgResponseHandler.java](/src/main/java/com/lxy/imapp/biz/socket/handler/impl/MsgResponseHandler.java) | Java | 22 | 0 | 6 | 28 |
| [src/main/java/com/lxy/imapp/biz/socket/handler/impl/SearchFriendHandler.java](/src/main/java/com/lxy/imapp/biz/socket/handler/impl/SearchFriendHandler.java) | Java | 30 | 0 | 10 | 40 |
| [src/main/java/com/lxy/imapp/biz/socket/po/UserGroupRequest.java](/src/main/java/com/lxy/imapp/biz/socket/po/UserGroupRequest.java) | Java | 23 | 0 | 6 | 29 |
| [src/main/java/com/lxy/imapp/biz/util/BeanUtil.java](/src/main/java/com/lxy/imapp/biz/util/BeanUtil.java) | Java | 14 | 0 | 7 | 21 |
| [src/main/java/com/lxy/imapp/biz/util/CacheUtil.java](/src/main/java/com/lxy/imapp/biz/util/CacheUtil.java) | Java | 4 | 0 | 3 | 7 |
| [src/main/java/com/lxy/imapp/front/ImUI.java](/src/main/java/com/lxy/imapp/front/ImUI.java) | Java | 20 | 0 | 11 | 31 |
| [src/main/java/com/lxy/imapp/front/constant/FriendPaneId.java](/src/main/java/com/lxy/imapp/front/constant/FriendPaneId.java) | Java | 12 | 0 | 4 | 16 |
| [src/main/java/com/lxy/imapp/front/constant/TalkType.java](/src/main/java/com/lxy/imapp/front/constant/TalkType.java) | Java | 13 | 0 | 6 | 19 |
| [src/main/java/com/lxy/imapp/front/controller/ChatController.java](/src/main/java/com/lxy/imapp/front/controller/ChatController.java) | Java | 736 | 101 | 282 | 1,119 |
| [src/main/java/com/lxy/imapp/front/controller/EmotionController.java](/src/main/java/com/lxy/imapp/front/controller/EmotionController.java) | Java | 144 | 0 | 24 | 168 |
| [src/main/java/com/lxy/imapp/front/controller/LoginController.java](/src/main/java/com/lxy/imapp/front/controller/LoginController.java) | Java | 170 | 6 | 61 | 237 |
| [src/main/java/com/lxy/imapp/front/data/GroupsData.java](/src/main/java/com/lxy/imapp/front/data/GroupsData.java) | Java | 31 | 0 | 12 | 43 |
| [src/main/java/com/lxy/imapp/front/data/RemindCount.java](/src/main/java/com/lxy/imapp/front/data/RemindCount.java) | Java | 15 | 0 | 8 | 23 |
| [src/main/java/com/lxy/imapp/front/data/TalkBoxData.java](/src/main/java/com/lxy/imapp/front/data/TalkBoxData.java) | Java | 39 | 0 | 14 | 53 |
| [src/main/java/com/lxy/imapp/front/data/TalkData.java](/src/main/java/com/lxy/imapp/front/data/TalkData.java) | Java | 22 | 0 | 11 | 33 |
| [src/main/java/com/lxy/imapp/front/element/chat_group/ElementInfoBox.java](/src/main/java/com/lxy/imapp/front/element/chat_group/ElementInfoBox.java) | Java | 108 | 15 | 31 | 154 |
| [src/main/java/com/lxy/imapp/front/element/chat_group/ElementTalk.java](/src/main/java/com/lxy/imapp/front/element/chat_group/ElementTalk.java) | Java | 111 | 14 | 33 | 158 |
| [src/main/java/com/lxy/imapp/front/element/friend_group/ElementCreateGroup.java](/src/main/java/com/lxy/imapp/front/element/friend_group/ElementCreateGroup.java) | Java | 79 | 6 | 26 | 111 |
| [src/main/java/com/lxy/imapp/front/element/friend_group/ElementFriendGroup.java](/src/main/java/com/lxy/imapp/front/element/friend_group/ElementFriendGroup.java) | Java | 36 | 5 | 8 | 49 |
| [src/main/java/com/lxy/imapp/front/element/friend_group/ElementFriendGroupList.java](/src/main/java/com/lxy/imapp/front/element/friend_group/ElementFriendGroupList.java) | Java | 31 | 3 | 8 | 42 |
| [src/main/java/com/lxy/imapp/front/element/friend_group/ElementFriendLuck.java](/src/main/java/com/lxy/imapp/front/element/friend_group/ElementFriendLuck.java) | Java | 67 | 7 | 18 | 92 |
| [src/main/java/com/lxy/imapp/front/element/friend_group/ElementFriendLuckUser.java](/src/main/java/com/lxy/imapp/front/element/friend_group/ElementFriendLuckUser.java) | Java | 69 | 17 | 9 | 95 |
| [src/main/java/com/lxy/imapp/front/element/friend_group/ElementFriendSubscription.java](/src/main/java/com/lxy/imapp/front/element/friend_group/ElementFriendSubscription.java) | Java | 60 | 3 | 15 | 78 |
| [src/main/java/com/lxy/imapp/front/element/friend_group/ElementFriendTag.java](/src/main/java/com/lxy/imapp/front/element/friend_group/ElementFriendTag.java) | Java | 23 | 0 | 6 | 29 |
| [src/main/java/com/lxy/imapp/front/element/friend_group/ElementFriendUser.java](/src/main/java/com/lxy/imapp/front/element/friend_group/ElementFriendUser.java) | Java | 35 | 3 | 7 | 45 |
| [src/main/java/com/lxy/imapp/front/element/friend_group/ElementFriendUserList.java](/src/main/java/com/lxy/imapp/front/element/friend_group/ElementFriendUserList.java) | Java | 31 | 0 | 6 | 37 |
| [src/main/java/com/lxy/imapp/front/element/friend_group/ElementGroupRequest.java](/src/main/java/com/lxy/imapp/front/element/friend_group/ElementGroupRequest.java) | Java | 65 | 8 | 25 | 98 |
| [src/main/java/com/lxy/imapp/front/element/friend_group/ElementJoinGroup.java](/src/main/java/com/lxy/imapp/front/element/friend_group/ElementJoinGroup.java) | Java | 68 | 7 | 17 | 92 |
| [src/main/java/com/lxy/imapp/front/element/friend_group/ElementJoinGroupItem.java](/src/main/java/com/lxy/imapp/front/element/friend_group/ElementJoinGroupItem.java) | Java | 70 | 14 | 11 | 95 |
| [src/main/java/com/lxy/imapp/front/element/friend_group/ElementNewFriend.java](/src/main/java/com/lxy/imapp/front/element/friend_group/ElementNewFriend.java) | Java | 68 | 5 | 26 | 99 |
| [src/main/java/com/lxy/imapp/front/element/friend_group/ElementNewFriendUser.java](/src/main/java/com/lxy/imapp/front/element/friend_group/ElementNewFriendUser.java) | Java | 73 | 14 | 18 | 105 |
| [src/main/java/com/lxy/imapp/front/element/friend_group/ElementNewGroupRequest.java](/src/main/java/com/lxy/imapp/front/element/friend_group/ElementNewGroupRequest.java) | Java | 109 | 10 | 27 | 146 |
| [src/main/java/com/lxy/imapp/front/util/AutoSizeTool.java](/src/main/java/com/lxy/imapp/front/util/AutoSizeTool.java) | Java | 39 | 1 | 15 | 55 |
| [src/main/java/com/lxy/imapp/front/util/CacheUtil.java](/src/main/java/com/lxy/imapp/front/util/CacheUtil.java) | Java | 7 | 1 | 8 | 16 |
| [src/main/java/com/lxy/imapp/front/util/DateUtil.java](/src/main/java/com/lxy/imapp/front/util/DateUtil.java) | Java | 26 | 0 | 9 | 35 |
| [src/main/java/com/lxy/imapp/front/util/Ids.java](/src/main/java/com/lxy/imapp/front/util/Ids.java) | Java | 32 | 3 | 14 | 49 |
| [src/main/java/com/lxy/imapp/front/view/Chat.java](/src/main/java/com/lxy/imapp/front/view/Chat.java) | Java | 52 | 0 | 20 | 72 |
| [src/main/java/com/lxy/imapp/front/view/Emotion.java](/src/main/java/com/lxy/imapp/front/view/Emotion.java) | Java | 42 | 0 | 15 | 57 |
| [src/main/java/com/lxy/imapp/front/view/Login.java](/src/main/java/com/lxy/imapp/front/view/Login.java) | Java | 62 | 2 | 25 | 89 |
| [src/main/resources/fxml/chat/chat.fxml](/src/main/resources/fxml/chat/chat.fxml) | XML | 113 | 11 | 17 | 141 |
| [src/main/resources/fxml/chat/css/chat.css](/src/main/resources/fxml/chat/css/chat.css) | CSS | 10 | 0 | 8 | 18 |
| [src/main/resources/fxml/chat/css/content.css](/src/main/resources/fxml/chat/css/content.css) | CSS | 47 | 0 | 20 | 67 |
| [src/main/resources/fxml/chat/css/friend.css](/src/main/resources/fxml/chat/css/friend.css) | CSS | 6 | 0 | 0 | 6 |
| [src/main/resources/fxml/chat/css/group_bar_chat/chat_information.css](/src/main/resources/fxml/chat/css/group_bar_chat/chat_information.css) | CSS | 147 | 1 | 30 | 178 |
| [src/main/resources/fxml/chat/css/group_bar_chat/chat_talk.css](/src/main/resources/fxml/chat/css/group_bar_chat/chat_talk.css) | CSS | 107 | 7 | 18 | 132 |
| [src/main/resources/fxml/chat/css/group_bar_chat/chat_tool.css](/src/main/resources/fxml/chat/css/group_bar_chat/chat_tool.css) | CSS | 16 | 0 | 2 | 18 |
| [src/main/resources/fxml/chat/css/group_bar_chat/chat_touch.css](/src/main/resources/fxml/chat/css/group_bar_chat/chat_touch.css) | CSS | 22 | 0 | 3 | 25 |
| [src/main/resources/fxml/chat/css/group_bar_chat/chat_txt.css](/src/main/resources/fxml/chat/css/group_bar_chat/chat_txt.css) | CSS | 65 | 0 | 12 | 77 |
| [src/main/resources/fxml/chat/css/group_bar_friend/chat_content.css](/src/main/resources/fxml/chat/css/group_bar_friend/chat_content.css) | CSS | 33 | 1 | 6 | 40 |
| [src/main/resources/fxml/chat/css/group_bar_friend/chat_friend.css](/src/main/resources/fxml/chat/css/group_bar_friend/chat_friend.css) | CSS | 98 | 0 | 19 | 117 |
| [src/main/resources/fxml/chat/css/group_bar_friend/chat_friend_group.css](/src/main/resources/fxml/chat/css/group_bar_friend/chat_friend_group.css) | CSS | 52 | 0 | 9 | 61 |
| [src/main/resources/fxml/chat/css/group_bar_friend/chat_friend_luck.css](/src/main/resources/fxml/chat/css/group_bar_friend/chat_friend_luck.css) | CSS | 128 | 1 | 22 | 151 |
| [src/main/resources/fxml/chat/css/group_bar_friend/chat_friend_user.css](/src/main/resources/fxml/chat/css/group_bar_friend/chat_friend_user.css) | CSS | 51 | 0 | 9 | 60 |
| [src/main/resources/fxml/chat/css/group_bar_friend/chat_new_friend_user.css](/src/main/resources/fxml/chat/css/group_bar_friend/chat_new_friend_user.css) | CSS | 20 | 1 | 1 | 22 |
| [src/main/resources/fxml/chat/css/operation.css](/src/main/resources/fxml/chat/css/operation.css) | CSS | 32 | 0 | 5 | 37 |
| [src/main/resources/fxml/chat/css/search.css](/src/main/resources/fxml/chat/css/search.css) | CSS | 33 | 0 | 6 | 39 |
| [src/main/resources/fxml/emotion/css/emotion.css](/src/main/resources/fxml/emotion/css/emotion.css) | CSS | 10 | 0 | 2 | 12 |
| [src/main/resources/fxml/emotion/emotion.fxml](/src/main/resources/fxml/emotion/emotion.fxml) | XML | 10 | 0 | 2 | 12 |
| [src/main/resources/fxml/login/css/login.css](/src/main/resources/fxml/login/css/login.css) | CSS | 82 | 15 | 28 | 125 |
| [src/main/resources/fxml/login/login.fxml](/src/main/resources/fxml/login/login.fxml) | XML | 39 | 0 | 27 | 66 |

[Summary](results.md) / Details / [Diff Summary](diff.md) / [Diff Details](diff-details.md)