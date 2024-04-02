package com.lxy.infrastructure.common;

import com.lxy.protocolpackage.protocol.Packet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 保存用户下线时收到的消息
 */
public class UserOffineMsgCache {
    private static Map<String, List<Packet>> userOfflineMsgCache = new ConcurrentHashMap<>();

    public static List<Packet> getOfflineMsgByUserId(String userId){
        return userOfflineMsgCache.get(userId);
    }

    public static void addOfflineMsgToUser(String userId, Packet msg){
        List<Packet> packets = userOfflineMsgCache.get(userId);
        if(packets == null){
            packets = new ArrayList<>();
            userOfflineMsgCache.put(userId, packets);
        }

        packets.add(msg);
    }


    public static Map<String, List<Packet>> getUserOfflineMap(){
        return userOfflineMsgCache;
    }


}
