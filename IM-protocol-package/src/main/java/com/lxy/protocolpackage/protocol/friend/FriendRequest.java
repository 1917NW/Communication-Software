package com.lxy.protocolpackage.protocol.friend;

import com.lxy.protocolpackage.protocol.Command;
import com.lxy.protocolpackage.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * userId向friendId申请加好友
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendRequest extends Packet {
    private String userId;    // 用户ID[自己的ID]
    private String friendId;  // 好友ID

    @Override
    public Byte getCommand() {
        return Command.FriendRequest;
    }
}
