package com.lxy.protocolpackage.protocol.register;

import com.lxy.protocolpackage.protocol.Command;
import com.lxy.protocolpackage.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterSuccessRequest extends Packet {
    String userId;

    @Override
    public Byte getCommand() {
        return Command.RegisterSuccessRequest;
    }
}
