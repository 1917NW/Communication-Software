package com.lxy.protocolpackage.protocol.group;

import com.lxy.protocolpackage.protocol.Command;
import com.lxy.protocolpackage.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupSearchRequest extends Packet {
    // 发起搜索请求的用户id
    String userId;

    // 搜索词
    String matchWord;

    @Override
    public Byte getCommand() {
        return Command.SearchGroupRequest;
    }
}
