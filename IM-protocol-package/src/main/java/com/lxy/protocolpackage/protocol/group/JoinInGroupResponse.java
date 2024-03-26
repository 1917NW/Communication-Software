package com.lxy.protocolpackage.protocol.group;

import com.lxy.protocolpackage.protocol.Command;
import com.lxy.protocolpackage.protocol.Packet;
import com.lxy.protocolpackage.protocol.group.dto.GroupDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JoinInGroupResponse extends Packet {
    GroupDto groupDto;

    @Override
    public Byte getCommand() {
        return Command.JoinInGroupResponse;
    }
}
