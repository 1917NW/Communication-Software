package com.lxy.protocolpackage.rpc;

import com.lxy.protocolpackage.protocol.friend.DeleteFriendResponse;

public interface DeleteFriendResponseRPC {

    void sendToUser(String userId, DeleteFriendResponse deleteFriendResponse);
}
