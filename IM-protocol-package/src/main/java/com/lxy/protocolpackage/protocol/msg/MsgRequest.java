package com.lxy.protocolpackage.protocol.msg;

import com.lxy.protocolpackage.protocol.Command;
import com.lxy.protocolpackage.protocol.Packet;
import com.lxy.protocolpackage.protocol.friend.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MsgRequest extends Packet {

    private UserDto userDto;   // 用户ID[个人]
    private String friendId; // 好友ID[对方]
    private String msgText;  // 传输消息内容
    private Integer msgType; // 消息类型；0文字消息、1固定表情
    private Date msgDate;    // 消息时间

    @Override
    public Byte getCommand() {
        return Command.MsgRequest;
    }
}