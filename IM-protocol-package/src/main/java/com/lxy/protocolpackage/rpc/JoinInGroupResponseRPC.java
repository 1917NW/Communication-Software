package com.lxy.protocolpackage.rpc;

import com.lxy.protocolpackage.protocol.group.JoinInGroupResponse;

public interface JoinInGroupResponseRPC {
    void sendToUserWithJoinInGroupResponse(String userId, JoinInGroupResponse joinInGroupResponse);
}
