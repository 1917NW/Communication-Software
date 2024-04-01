package com.lxy.protocolpackage.protocol.msg;

import com.lxy.protocolpackage.dto.UserDto;
import com.lxy.protocolpackage.protocol.Command;
import com.lxy.protocolpackage.protocol.Packet;
import com.lxy.protocolpackage.dto.ChatTalkDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MsgResponse extends Packet {

    private UserDto userDto; // 发送消息方的信息

    private String msgText;  // 传输消息内容
    private Integer msgType;  // 消息类型；0文字消息、1固定表情
    private Date msgDate;    // 消息时间

    @Override
    public Byte getCommand() {
        return Command.MsgResponse;
    }
}