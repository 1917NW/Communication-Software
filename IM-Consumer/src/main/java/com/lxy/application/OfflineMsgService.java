package com.lxy.application;

import com.lxy.infrastructure.entity.ImOfflineMsg;
import com.lxy.protocolpackage.protocol.Packet;

import java.util.List;

public interface OfflineMsgService {

    public void storeOfflineMsg(String userId, Packet packet);



    void storeBatchOfflineMsg(String userId, List<Packet> packetList);


    void storeBatchOfflineGroupMsg(List<String> userIdList, Packet packet);

    List<ImOfflineMsg> getOfflineMsgByUserId(String userId);

}
