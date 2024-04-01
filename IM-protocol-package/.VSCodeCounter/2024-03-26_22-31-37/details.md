# Details

Date : 2024-03-26 22:31:37

Directory c:\\Users\\26327\\Desktop\\毕业设计\\通讯软件\\IM-protocol-package

Total : 47 files,  1105 codes, 52 comments, 287 blanks, all 1444 lines

[Summary](results.md) / Details / [Diff Summary](diff.md) / [Diff Details](diff-details.md)

## Files
| filename | language | code | comment | blank | total |
| :--- | :--- | ---: | ---: | ---: | ---: |
| [.idea/compiler.xml](/.idea/compiler.xml) | XML | 17 | 0 | 0 | 17 |
| [.idea/encodings.xml](/.idea/encodings.xml) | XML | 7 | 0 | 0 | 7 |
| [.idea/jarRepositories.xml](/.idea/jarRepositories.xml) | XML | 20 | 0 | 0 | 20 |
| [.idea/misc.xml](/.idea/misc.xml) | XML | 14 | 0 | 0 | 14 |
| [.idea/modules.xml](/.idea/modules.xml) | XML | 8 | 0 | 0 | 8 |
| [.idea/uiDesigner.xml](/.idea/uiDesigner.xml) | XML | 124 | 0 | 0 | 124 |
| [.idea/vcs.xml](/.idea/vcs.xml) | XML | 6 | 0 | 0 | 6 |
| [IM-protocol-package.iml](/IM-protocol-package.iml) | XML | 8 | 0 | 0 | 8 |
| [pom.xml](/pom.xml) | XML | 53 | 2 | 4 | 59 |
| [src/main/java/com/lxy/protocolpackage/codec/ObjDecoder.java](/src/main/java/com/lxy/protocolpackage/codec/ObjDecoder.java) | Java | 25 | 2 | 7 | 34 |
| [src/main/java/com/lxy/protocolpackage/codec/ObjEncoder.java](/src/main/java/com/lxy/protocolpackage/codec/ObjEncoder.java) | Java | 15 | 4 | 4 | 23 |
| [src/main/java/com/lxy/protocolpackage/constants/FriendState.java](/src/main/java/com/lxy/protocolpackage/constants/FriendState.java) | Java | 11 | 0 | 4 | 15 |
| [src/main/java/com/lxy/protocolpackage/constants/GroupState.java](/src/main/java/com/lxy/protocolpackage/constants/GroupState.java) | Java | 11 | 0 | 3 | 14 |
| [src/main/java/com/lxy/protocolpackage/constants/MsgType.java](/src/main/java/com/lxy/protocolpackage/constants/MsgType.java) | Java | 12 | 2 | 5 | 19 |
| [src/main/java/com/lxy/protocolpackage/constants/TalkType.java](/src/main/java/com/lxy/protocolpackage/constants/TalkType.java) | Java | 13 | 2 | 7 | 22 |
| [src/main/java/com/lxy/protocolpackage/protocol/Command.java](/src/main/java/com/lxy/protocolpackage/protocol/Command.java) | Java | 27 | 0 | 17 | 44 |
| [src/main/java/com/lxy/protocolpackage/protocol/Packet.java](/src/main/java/com/lxy/protocolpackage/protocol/Packet.java) | Java | 42 | 16 | 20 | 78 |
| [src/main/java/com/lxy/protocolpackage/protocol/friend/AddFriendRequest.java](/src/main/java/com/lxy/protocolpackage/protocol/friend/AddFriendRequest.java) | Java | 18 | 3 | 7 | 28 |
| [src/main/java/com/lxy/protocolpackage/protocol/friend/AddFriendResponse.java](/src/main/java/com/lxy/protocolpackage/protocol/friend/AddFriendResponse.java) | Java | 18 | 3 | 8 | 29 |
| [src/main/java/com/lxy/protocolpackage/protocol/friend/FriendRequest.java](/src/main/java/com/lxy/protocolpackage/protocol/friend/FriendRequest.java) | Java | 17 | 3 | 4 | 24 |
| [src/main/java/com/lxy/protocolpackage/protocol/friend/FriendResponse.java](/src/main/java/com/lxy/protocolpackage/protocol/friend/FriendResponse.java) | Java | 18 | 0 | 5 | 23 |
| [src/main/java/com/lxy/protocolpackage/protocol/friend/SearchFriendRequest.java](/src/main/java/com/lxy/protocolpackage/protocol/friend/SearchFriendRequest.java) | Java | 29 | 0 | 11 | 40 |
| [src/main/java/com/lxy/protocolpackage/protocol/friend/SearchFriendResponse.java](/src/main/java/com/lxy/protocolpackage/protocol/friend/SearchFriendResponse.java) | Java | 18 | 0 | 7 | 25 |
| [src/main/java/com/lxy/protocolpackage/protocol/friend/dto/UserDto.java](/src/main/java/com/lxy/protocolpackage/protocol/friend/dto/UserDto.java) | Java | 38 | 0 | 12 | 50 |
| [src/main/java/com/lxy/protocolpackage/protocol/group/FullGroupJoinInGroupResponse.java](/src/main/java/com/lxy/protocolpackage/protocol/group/FullGroupJoinInGroupResponse.java) | Java | 19 | 0 | 4 | 23 |
| [src/main/java/com/lxy/protocolpackage/protocol/group/FullUserJoinInGroupRequest.java](/src/main/java/com/lxy/protocolpackage/protocol/group/FullUserJoinInGroupRequest.java) | Java | 19 | 0 | 6 | 25 |
| [src/main/java/com/lxy/protocolpackage/protocol/group/GroupCreateRequest.java](/src/main/java/com/lxy/protocolpackage/protocol/group/GroupCreateRequest.java) | Java | 19 | 0 | 4 | 23 |
| [src/main/java/com/lxy/protocolpackage/protocol/group/GroupCreateResponse.java](/src/main/java/com/lxy/protocolpackage/protocol/group/GroupCreateResponse.java) | Java | 18 | 0 | 4 | 22 |
| [src/main/java/com/lxy/protocolpackage/protocol/group/GroupSearchRequest.java](/src/main/java/com/lxy/protocolpackage/protocol/group/GroupSearchRequest.java) | Java | 17 | 2 | 5 | 24 |
| [src/main/java/com/lxy/protocolpackage/protocol/group/GroupSearchResponse.java](/src/main/java/com/lxy/protocolpackage/protocol/group/GroupSearchResponse.java) | Java | 18 | 0 | 5 | 23 |
| [src/main/java/com/lxy/protocolpackage/protocol/group/JoinInGroupRequest.java](/src/main/java/com/lxy/protocolpackage/protocol/group/JoinInGroupRequest.java) | Java | 18 | 0 | 6 | 24 |
| [src/main/java/com/lxy/protocolpackage/protocol/group/JoinInGroupResponse.java](/src/main/java/com/lxy/protocolpackage/protocol/group/JoinInGroupResponse.java) | Java | 17 | 0 | 4 | 21 |
| [src/main/java/com/lxy/protocolpackage/protocol/group/MsgGroupRequest.java](/src/main/java/com/lxy/protocolpackage/protocol/group/MsgGroupRequest.java) | Java | 21 | 0 | 8 | 29 |
| [src/main/java/com/lxy/protocolpackage/protocol/group/MsgGroupResponse.java](/src/main/java/com/lxy/protocolpackage/protocol/group/MsgGroupResponse.java) | Java | 22 | 0 | 9 | 31 |
| [src/main/java/com/lxy/protocolpackage/protocol/group/dto/GroupDto.java](/src/main/java/com/lxy/protocolpackage/protocol/group/dto/GroupDto.java) | Java | 14 | 0 | 4 | 18 |
| [src/main/java/com/lxy/protocolpackage/protocol/login/LoginRequest.java](/src/main/java/com/lxy/protocolpackage/protocol/login/LoginRequest.java) | Java | 17 | 0 | 7 | 24 |
| [src/main/java/com/lxy/protocolpackage/protocol/login/LoginResponse.java](/src/main/java/com/lxy/protocolpackage/protocol/login/LoginResponse.java) | Java | 27 | 0 | 5 | 32 |
| [src/main/java/com/lxy/protocolpackage/protocol/login/dto/ChatRecordDto.java](/src/main/java/com/lxy/protocolpackage/protocol/login/dto/ChatRecordDto.java) | Java | 60 | 0 | 21 | 81 |
| [src/main/java/com/lxy/protocolpackage/protocol/login/dto/ChatTalkDto.java](/src/main/java/com/lxy/protocolpackage/protocol/login/dto/ChatTalkDto.java) | Java | 54 | 0 | 20 | 74 |
| [src/main/java/com/lxy/protocolpackage/protocol/login/dto/GroupsDto.java](/src/main/java/com/lxy/protocolpackage/protocol/login/dto/GroupsDto.java) | Java | 24 | 0 | 10 | 34 |
| [src/main/java/com/lxy/protocolpackage/protocol/login/dto/UserFriendDto.java](/src/main/java/com/lxy/protocolpackage/protocol/login/dto/UserFriendDto.java) | Java | 24 | 0 | 10 | 34 |
| [src/main/java/com/lxy/protocolpackage/protocol/msg/MsgRequest.java](/src/main/java/com/lxy/protocolpackage/protocol/msg/MsgRequest.java) | Java | 22 | 0 | 5 | 27 |
| [src/main/java/com/lxy/protocolpackage/protocol/msg/MsgResponse.java](/src/main/java/com/lxy/protocolpackage/protocol/msg/MsgResponse.java) | Java | 22 | 0 | 6 | 28 |
| [src/main/java/com/lxy/protocolpackage/protocol/talk/DelTalkRequest.java](/src/main/java/com/lxy/protocolpackage/protocol/talk/DelTalkRequest.java) | Java | 18 | 0 | 5 | 23 |
| [src/main/java/com/lxy/protocolpackage/protocol/talk/TalkNoticeRequest.java](/src/main/java/com/lxy/protocolpackage/protocol/talk/TalkNoticeRequest.java) | Java | 18 | 0 | 4 | 22 |
| [src/main/java/com/lxy/protocolpackage/util/SerializationUtil.java](/src/main/java/com/lxy/protocolpackage/util/SerializationUtil.java) | Java | 45 | 13 | 9 | 67 |
| [target/maven-archiver/pom.properties](/target/maven-archiver/pom.properties) | Properties | 3 | 0 | 1 | 4 |

[Summary](results.md) / Details / [Diff Summary](diff.md) / [Diff Details](diff-details.md)