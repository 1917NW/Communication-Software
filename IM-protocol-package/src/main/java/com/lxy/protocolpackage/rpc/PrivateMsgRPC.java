package com.lxy.protocolpackage.rpc;

import com.lxy.protocolpackage.protocol.msg.MsgResponse;

public interface PrivateMsgRPC {
    public void sendToUser(String userId, MsgResponse msgResponse);

}
