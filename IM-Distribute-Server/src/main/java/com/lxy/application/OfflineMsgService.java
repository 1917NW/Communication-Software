package com.lxy.application;

import com.lxy.infrastructure.entity.ImOfflineMsg;
import com.lxy.protocolpackage.protocol.Packet;

import java.util.List;

public interface OfflineMsgService {

    public void storeOfflineMsg(String serverId, String userId, Packet packet);



    void storeBatchOfflineMsg(String serverId, String userId, List<Packet> packetList);

    List<ImOfflineMsg> getOfflineMsgByServerId(String serverId);

    List<ImOfflineMsg> getOfflineMsgByServerIdAndUserId(String serverId, String userId);

}
