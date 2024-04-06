package com.lxy.service;

import com.lxy.protocolpackage.protocol.msg.MsgResponse;
import com.lxy.protocolpackage.rpc.PrivateMsgRPC;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

//@Component
public class PrivateMsgService {
    @DubboReference
    private PrivateMsgRPC privateMsgRPC;

    public void show(){
        privateMsgRPC.sendToUser("123", new MsgResponse());
    }
}
