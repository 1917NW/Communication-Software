package com.lxy.protocolpackage.protocol.friend;

import com.lxy.protocolpackage.protocol.Command;
import com.lxy.protocolpackage.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * userId向friendId发起好友申请
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddFriendRequest extends Packet {

    private String requestFriendId;
    private String requestFriendNickName;
    private String requestFriendHead;


    @Override
    public Byte getCommand() {
        return Command.AddFriendRequest;
    }

}
