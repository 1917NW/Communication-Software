package com.lxy.protocolpackage.protocol.init;

import com.lxy.protocolpackage.protocol.Command;
import com.lxy.protocolpackage.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InitSuccessResponse extends Packet {

    String userId;

    @Override
    public Byte getCommand() {
        return Command.InitSuccessResponse;
    }
}
