package com.lxy.protocolpackage.protocol.group;

import com.lxy.protocolpackage.protocol.Command;
import com.lxy.protocolpackage.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class JoinInGroupRequest extends Packet {

    String userId; // 申请人
    String groupId; // 申请的组
    String leaderId; // 申请的组的组长

    @Override
    public Byte getCommand() {
        return Command.JoinInGroupRequest;
    }
}
