package com.lxy.protocolpackage.protocol.friend;


import com.lxy.protocolpackage.protocol.Command;
import com.lxy.protocolpackage.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteFriendRequest  extends Packet {

    private String invokerId;

    private String deletedUserId;

    @Override
    public Byte getCommand() {
        return Command.DeleteFriendRequest;
    }
}
