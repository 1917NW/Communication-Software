package com.lxy.protocolpackage.protocol.friend;

import com.lxy.protocolpackage.protocol.Command;
import com.lxy.protocolpackage.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * userId同意了requestFriendId的好友申请
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddFriendResponse extends Packet {

    private String userId;    // 用户ID[自己的ID]
    private String requestFriendId;  // 好友ID



    @Override
    public Byte getCommand() {
        return Command.AddFriendResponse;
    }
}
