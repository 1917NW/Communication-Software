package com.lxy.protocolpackage.protocol.group;

import com.lxy.protocolpackage.protocol.Command;
import com.lxy.protocolpackage.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupCreateRequest extends Packet {
    String groupId;
    String groupName;
    String groupHead;
    String groupLeaderId;

    @Override
    public Byte getCommand() {
        return Command.CreateGroupRequest;
    }
}
