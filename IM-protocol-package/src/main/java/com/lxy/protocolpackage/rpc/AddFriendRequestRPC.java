package com.lxy.protocolpackage.rpc;

import com.lxy.protocolpackage.protocol.friend.AddFriendRequest;

public interface AddFriendRequestRPC {
    void sendToUserWithFriendRequest(String userId, AddFriendRequest addFriendRequest);
}
