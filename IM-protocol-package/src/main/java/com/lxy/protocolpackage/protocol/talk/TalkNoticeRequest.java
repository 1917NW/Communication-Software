package com.lxy.protocolpackage.protocol.talk;

import com.lxy.protocolpackage.protocol.Command;
import com.lxy.protocolpackage.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalkNoticeRequest extends Packet {

    private String userId;       // 用户ID
    private String talkId; // 对话ID[好友、群组]
    private Integer talkType;    // 对话框类型[0好友、1群组]

    @Override
    public Byte getCommand() {
        return Command.TalkNoticeRequest;
    }
}