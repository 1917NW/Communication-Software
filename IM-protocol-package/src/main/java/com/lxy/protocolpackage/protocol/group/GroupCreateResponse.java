package com.lxy.protocolpackage.protocol.group;

import com.lxy.protocolpackage.protocol.Command;
import com.lxy.protocolpackage.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupCreateResponse extends Packet {
    String groupId;
    String groupName;
    String groupHead;

    @Override
    public Byte getCommand() {
        return Command.CreateGroupResponse;
    }
}
