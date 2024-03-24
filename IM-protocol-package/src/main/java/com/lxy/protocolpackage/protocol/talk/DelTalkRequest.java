package com.lxy.protocolpackage.protocol.talk;

import com.lxy.protocolpackage.protocol.Command;
import com.lxy.protocolpackage.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DelTalkRequest extends Packet {

    private String userId;  // 用户ID
    private String talkId;  // 对话框ID

    private Integer talkType; // 对话框类型

    @Override
    public Byte getCommand() {
        return Command.DelTalkRequest;
    }
}