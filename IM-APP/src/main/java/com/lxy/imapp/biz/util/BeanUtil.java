package com.lxy.imapp.biz.util;

import io.netty.channel.Channel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanUtil {

    private static Map<String, Object> cacheMap = new ConcurrentHashMap<>();

    public static synchronized void addBean(String name, Object obj) {
        cacheMap.put(name, obj);
    }

    public static <T> T getBean(String name, Class<T> t) {
        return (T) cacheMap.get(name);
    }

    public static Channel getChannel(){return BeanUtil.getBean("channel", Channel.class);}



    public static String getUserId(){return BeanUtil.getBean("userId", String.class);}

    public static String getUserNickName(){return BeanUtil.getBean("userNickName", String.class);}

    public static String getUserHead(){
        return BeanUtil.getBean("userHead", String.class);
    }

}