package com.lxy.protocolpackage.protocol.login;

import com.lxy.protocolpackage.protocol.Command;
import com.lxy.protocolpackage.protocol.Packet;
import com.lxy.protocolpackage.dto.ChatTalkDto;
import com.lxy.protocolpackage.dto.GroupsDto;
import com.lxy.protocolpackage.dto.UserFriendDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse extends Packet {

    private boolean success;                    // 登陆反馈
    private String userId;                      // 用户ID
    private String userHead;                    // 用户头像
    private String userNickName;                // 用户昵称

    private List<GroupsDto> groupsList = new ArrayList<>();         // 群组列表
    private List<UserFriendDto> userFriendList = new ArrayList<>(); // 好友列表

    @Override
    public Byte getCommand() {
        return Command.LoginResponse;
    }
}