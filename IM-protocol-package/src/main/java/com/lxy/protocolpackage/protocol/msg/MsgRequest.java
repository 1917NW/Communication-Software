package com.lxy.protocolpackage.protocol.msg;

import com.lxy.protocolpackage.dto.UserDto;
import com.lxy.protocolpackage.protocol.Command;
import com.lxy.protocolpackage.protocol.Packet;

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
    private String msgText;  // 传输消息内容，如果为文字消息，则为文字内容，如果为表情，则为表情对应的编码，如果为文件，则为minio中文件路径
    private Integer msgType; // 消息类型；0文字消息、1固定表情、2文件
    private Date msgDate;    // 消息时间

    @Override
    public Byte getCommand() {
        return Command.MsgRequest;
    }
}