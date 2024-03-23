package com.lxy.protocolpackage.protocol.friend;

import com.lxy.protocolpackage.protocol.Command;
import com.lxy.protocolpackage.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FriendResponse extends Packet {

    private String agreeFriendId;
    private String agreeFriendNickName;
    private String agreeFriendHead;

    @Override
    public Byte getCommand() {
        return Command.FriendResponse;
    }
}
