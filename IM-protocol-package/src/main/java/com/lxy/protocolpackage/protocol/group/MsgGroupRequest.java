package com.lxy.protocolpackage.protocol.group;

import com.lxy.protocolpackage.dto.UserDto;
import com.lxy.protocolpackage.protocol.Command;
import com.lxy.protocolpackage.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MsgGroupRequest extends Packet {
    private UserDto userDto;

    private String groupId;

    private String msgText;

    private Integer msgType;

    private Date msgDate;

    @Override
    public Byte getCommand() {
        return Command.MsgGroupRequest;
    }
}
