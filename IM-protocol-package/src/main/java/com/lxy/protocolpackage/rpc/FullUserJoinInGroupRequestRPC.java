package com.lxy.protocolpackage.rpc;

import com.lxy.protocolpackage.protocol.group.FullUserJoinInGroupRequest;

public interface FullUserJoinInGroupRequestRPC {
    void sendToUserWithFullUserJoinInGroupRequest(String userId, FullUserJoinInGroupRequest fullUserJoinInGroupRequest);
}
