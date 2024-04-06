package com.lxy.protocolpackage.rpc;

import com.lxy.protocolpackage.protocol.group.MsgGroupResponse;
import com.lxy.protocolpackage.protocol.msg.MsgResponse;

public interface GroupMsgRPC {
    public void sendToGroup(String groupId, MsgGroupResponse msgGroupResponse);
}
