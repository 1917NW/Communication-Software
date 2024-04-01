# Details

Date : 2024-03-26 22:31:09

Directory c:\\Users\\26327\\Desktop\\毕业设计\\通讯软件\\IM-Server

Total : 49 files,  1532 codes, 251 comments, 451 blanks, all 2234 lines

[Summary](results.md) / Details / [Diff Summary](diff.md) / [Diff Details](diff-details.md)

## Files
| filename | language | code | comment | blank | total |
| :--- | :--- | ---: | ---: | ---: | ---: |
| [pom.xml](/pom.xml) | XML | 111 | 0 | 8 | 119 |
| [src/main/java/com/lxy/ImServerApplication.java](/src/main/java/com/lxy/ImServerApplication.java) | Java | 9 | 0 | 5 | 14 |
| [src/main/java/com/lxy/application/UserService.java](/src/main/java/com/lxy/application/UserService.java) | Java | 27 | 92 | 30 | 149 |
| [src/main/java/com/lxy/domain/user/model/ChatRecordInfo.java](/src/main/java/com/lxy/domain/user/model/ChatRecordInfo.java) | Java | 16 | 0 | 4 | 20 |
| [src/main/java/com/lxy/domain/user/model/GroupsInfo.java](/src/main/java/com/lxy/domain/user/model/GroupsInfo.java) | Java | 13 | 0 | 5 | 18 |
| [src/main/java/com/lxy/domain/user/model/LuckUserInfo.java](/src/main/java/com/lxy/domain/user/model/LuckUserInfo.java) | Java | 13 | 3 | 5 | 21 |
| [src/main/java/com/lxy/domain/user/model/TalkBoxInfo.java](/src/main/java/com/lxy/domain/user/model/TalkBoxInfo.java) | Java | 16 | 0 | 4 | 20 |
| [src/main/java/com/lxy/domain/user/model/UserFriendInfo.java](/src/main/java/com/lxy/domain/user/model/UserFriendInfo.java) | Java | 12 | 0 | 2 | 14 |
| [src/main/java/com/lxy/domain/user/model/UserGroupInfo.java](/src/main/java/com/lxy/domain/user/model/UserGroupInfo.java) | Java | 12 | 0 | 2 | 14 |
| [src/main/java/com/lxy/domain/user/model/UserInfo.java](/src/main/java/com/lxy/domain/user/model/UserInfo.java) | Java | 12 | 0 | 2 | 14 |
| [src/main/java/com/lxy/domain/user/service/UserServiceImpl.java](/src/main/java/com/lxy/domain/user/service/UserServiceImpl.java) | Java | 271 | 3 | 53 | 327 |
| [src/main/java/com/lxy/infrastructure/common/SocketChannelUtil.java](/src/main/java/com/lxy/infrastructure/common/SocketChannelUtil.java) | Java | 50 | 3 | 18 | 71 |
| [src/main/java/com/lxy/infrastructure/common/UserOffineMsgCache.java](/src/main/java/com/lxy/infrastructure/common/UserOffineMsgCache.java) | Java | 20 | 3 | 8 | 31 |
| [src/main/java/com/lxy/infrastructure/dao/ImGroupDao.java](/src/main/java/com/lxy/infrastructure/dao/ImGroupDao.java) | Java | 10 | 6 | 8 | 24 |
| [src/main/java/com/lxy/infrastructure/dao/ImUserDao.java](/src/main/java/com/lxy/infrastructure/dao/ImUserDao.java) | Java | 10 | 12 | 7 | 29 |
| [src/main/java/com/lxy/infrastructure/dao/ImUserFriendDao.java](/src/main/java/com/lxy/infrastructure/dao/ImUserFriendDao.java) | Java | 10 | 6 | 7 | 23 |
| [src/main/java/com/lxy/infrastructure/dao/ImUserGroupDao.java](/src/main/java/com/lxy/infrastructure/dao/ImUserGroupDao.java) | Java | 11 | 6 | 8 | 25 |
| [src/main/java/com/lxy/infrastructure/dao/ImUserMsgDao.java](/src/main/java/com/lxy/infrastructure/dao/ImUserMsgDao.java) | Java | 11 | 6 | 7 | 24 |
| [src/main/java/com/lxy/infrastructure/dao/ImUserTalkDao.java](/src/main/java/com/lxy/infrastructure/dao/ImUserTalkDao.java) | Java | 11 | 6 | 8 | 25 |
| [src/main/java/com/lxy/infrastructure/entity/ImGroup.java](/src/main/java/com/lxy/infrastructure/entity/ImGroup.java) | Java | 19 | 13 | 12 | 44 |
| [src/main/java/com/lxy/infrastructure/entity/ImUser.java](/src/main/java/com/lxy/infrastructure/entity/ImUser.java) | Java | 22 | 13 | 9 | 44 |
| [src/main/java/com/lxy/infrastructure/entity/ImUserFriend.java](/src/main/java/com/lxy/infrastructure/entity/ImUserFriend.java) | Java | 28 | 11 | 11 | 50 |
| [src/main/java/com/lxy/infrastructure/entity/ImUserGroup.java](/src/main/java/com/lxy/infrastructure/entity/ImUserGroup.java) | Java | 18 | 11 | 9 | 38 |
| [src/main/java/com/lxy/infrastructure/entity/ImUserMsg.java](/src/main/java/com/lxy/infrastructure/entity/ImUserMsg.java) | Java | 22 | 12 | 10 | 44 |
| [src/main/java/com/lxy/infrastructure/entity/ImUserTalk.java](/src/main/java/com/lxy/infrastructure/entity/ImUserTalk.java) | Java | 19 | 12 | 9 | 40 |
| [src/main/java/com/lxy/socket/IMServerChannelInitializer.java](/src/main/java/com/lxy/socket/IMServerChannelInitializer.java) | Java | 40 | 0 | 5 | 45 |
| [src/main/java/com/lxy/socket/NettyServer.java](/src/main/java/com/lxy/socket/NettyServer.java) | Java | 57 | 1 | 18 | 76 |
| [src/main/java/com/lxy/socket/handler/AbstractBizHandler.java](/src/main/java/com/lxy/socket/handler/AbstractBizHandler.java) | Java | 26 | 0 | 9 | 35 |
| [src/main/java/com/lxy/socket/handler/impl/LoginHandler.java](/src/main/java/com/lxy/socket/handler/impl/LoginHandler.java) | Java | 143 | 13 | 27 | 183 |
| [src/main/java/com/lxy/socket/handler/impl/SearchFriendHandler.java](/src/main/java/com/lxy/socket/handler/impl/SearchFriendHandler.java) | Java | 42 | 0 | 9 | 51 |
| [src/main/java/com/lxy/socket/handler/impl/friend/AddFriendResponseHandler.java](/src/main/java/com/lxy/socket/handler/impl/friend/AddFriendResponseHandler.java) | Java | 40 | 5 | 11 | 56 |
| [src/main/java/com/lxy/socket/handler/impl/friend/FriendRequestHandler.java](/src/main/java/com/lxy/socket/handler/impl/friend/FriendRequestHandler.java) | Java | 35 | 1 | 10 | 46 |
| [src/main/java/com/lxy/socket/handler/impl/group/FullGroupJoinInGroupResponseHandler.java](/src/main/java/com/lxy/socket/handler/impl/group/FullGroupJoinInGroupResponseHandler.java) | Java | 34 | 1 | 9 | 44 |
| [src/main/java/com/lxy/socket/handler/impl/group/GroupCreateRequestHandler.java](/src/main/java/com/lxy/socket/handler/impl/group/GroupCreateRequestHandler.java) | Java | 32 | 0 | 10 | 42 |
| [src/main/java/com/lxy/socket/handler/impl/group/GroupSearchRequestHandler.java](/src/main/java/com/lxy/socket/handler/impl/group/GroupSearchRequestHandler.java) | Java | 26 | 0 | 7 | 33 |
| [src/main/java/com/lxy/socket/handler/impl/group/JoinInGroupRequestHandler.java](/src/main/java/com/lxy/socket/handler/impl/group/JoinInGroupRequestHandler.java) | Java | 43 | 0 | 11 | 54 |
| [src/main/java/com/lxy/socket/handler/impl/group/MsgGroupRequestHandler.java](/src/main/java/com/lxy/socket/handler/impl/group/MsgGroupRequestHandler.java) | Java | 41 | 4 | 10 | 55 |
| [src/main/java/com/lxy/socket/handler/impl/msg/MsgRequestHandler.java](/src/main/java/com/lxy/socket/handler/impl/msg/MsgRequestHandler.java) | Java | 43 | 2 | 10 | 55 |
| [src/main/java/com/lxy/socket/handler/impl/talk/DelTalkRequestHandler.java](/src/main/java/com/lxy/socket/handler/impl/talk/DelTalkRequestHandler.java) | Java | 21 | 0 | 5 | 26 |
| [src/main/java/com/lxy/socket/handler/impl/talk/TalkNoticeRequestHandler.java](/src/main/java/com/lxy/socket/handler/impl/talk/TalkNoticeRequestHandler.java) | Java | 21 | 0 | 6 | 27 |
| [src/main/java/com/lxy/socket/util/GroupIdGenerator.java](/src/main/java/com/lxy/socket/util/GroupIdGenerator.java) | Java | 12 | 0 | 7 | 19 |
| [src/main/resources/application.yaml](/src/main/resources/application.yaml) | YAML | 9 | 0 | 6 | 15 |
| [src/main/resources/mapper/ChatRecordMapper.xml](/src/main/resources/mapper/ChatRecordMapper.xml) | XML | 15 | 1 | 4 | 20 |
| [src/main/resources/mapper/ImGroupMapper.xml](/src/main/resources/mapper/ImGroupMapper.xml) | XML | 10 | 1 | 5 | 16 |
| [src/main/resources/mapper/ImUserFriendMapper.xml](/src/main/resources/mapper/ImUserFriendMapper.xml) | XML | 10 | 1 | 3 | 14 |
| [src/main/resources/mapper/ImUserGroupMapper.xml](/src/main/resources/mapper/ImUserGroupMapper.xml) | XML | 11 | 1 | 3 | 15 |
| [src/main/resources/mapper/ImUserMapper.xml](/src/main/resources/mapper/ImUserMapper.xml) | XML | 10 | 1 | 3 | 14 |
| [src/main/resources/mapper/ImUserTalk.xml](/src/main/resources/mapper/ImUserTalk.xml) | XML | 11 | 1 | 3 | 15 |
| [src/test/java/com/lxy/ImServerApplicationTests.java](/src/test/java/com/lxy/ImServerApplicationTests.java) | Java | 27 | 0 | 9 | 36 |

[Summary](results.md) / Details / [Diff Summary](diff.md) / [Diff Details](diff-details.md)