package com.lxy.protocolpackage.protocol.group;

import com.lxy.protocolpackage.protocol.Command;
import com.lxy.protocolpackage.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExitGroupRequest extends Packet {

    private String invokerId;

    private String deletedGroupId;
    @Override
    public Byte getCommand() {
        return Command.ExitGroupRequest;
    }
}
