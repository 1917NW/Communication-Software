package com.lxy.infrastructure.common;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SocketChannelUtil {

    // 用户id : 与用户通信的channel
    private static Map<String, Channel> userChannel = new ConcurrentHashMap<>();

    // 与用户通信的channel的id : 用户id
    private static Map<String, String> userChannelId = new ConcurrentHashMap<>();

    // 群组id : 与群组通信的channel
    private static Map<String, ChannelGroup> channelGroupMap = new ConcurrentHashMap<>();


    public  static void addChannelGroup(String groupId, Channel userChannel){
        ChannelGroup channelGroup = channelGroupMap.get(groupId);
        if(channelGroup == null){
            channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
            channelGroupMap.put(groupId, channelGroup);
        }
        channelGroup.add(userChannel);
    }
    
    public  static void removeChannelFromGroup(String groupId, Channel userChannel){
        ChannelGroup channelGroup = channelGroupMap.get(groupId);
        if(channelGroup == null)
            return;
        channelGroup.remove(userChannel);

    }

    public static void removeChannelFromAllGroup(Channel channel){
        for(ChannelGroup next : channelGroupMap.values()){
            next.remove(channel);
        }
    }


    public static  ChannelGroup getChannelGroupById(String groupId){
        return channelGroupMap.get(groupId);
    }
    public static void addChannel(String userId, Channel channel){
        System.out.println("user"+userId + "加入channel");
        userChannel.put(userId, channel);
        userChannelId.put(channel.id().toString(), userId);
    }

    public static void removeChannel(String channelId){

        String userId = userChannelId.get(channelId);
        if(userId == null)
            return;
        userChannel.remove(userId);
        System.out.println("user"+ userId + "移出channel");

    }

    public static String getUserIdByChannel(Channel channel){
        return userChannelId.get(channel.id().toString());
    }

    public static void removeUserChannelByUserId(String userId){
        System.out.println("user"+ userId + "移出channel");
        userChannel.remove(userId);
    }

    public static Channel getChannel(String userId){
        System.out.println("user"+ userId + "获取channel");
        return userChannel.get(userId);
    }

}
