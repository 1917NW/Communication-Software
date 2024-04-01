package com.lxy.protocolpackage.protocol.group;

import com.lxy.protocolpackage.protocol.Command;
import com.lxy.protocolpackage.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MsgGroupResponse extends Packet {

    private String groupId;

    private String groupName;

    private String groupHead;

    private String userId;

    private String userNickName;

    private String userHead;

    private String msg;

    private Integer msgType;
    private Date msgDate;
    @Override
    public Byte getCommand() {
        return Command.MsgGroupResponse;
    }
}
