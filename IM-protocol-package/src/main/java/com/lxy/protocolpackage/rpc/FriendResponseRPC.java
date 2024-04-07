package com.lxy.protocolpackage.rpc;

import com.lxy.protocolpackage.protocol.friend.FriendResponse;

public interface FriendResponseRPC {
    void sendToUserWithFriendResponse(String userId, FriendResponse friendResponse);
}
