package com.lxy.protocolpackage.rediskey;

public class ImServerKey {
    public static String IM_SERVER_PREFIX = "im_server:";

    public static String IM_USER_PREFIX = "im_user:";

    public static String buildUserIdKey(String userId){
        return IM_USER_PREFIX + userId;
    }

    public static String buildServerUrlKey(String uri, Integer port){
        return IM_SERVER_PREFIX + uri + "-" + port;
    }

    public static String buildServerUrlKey(String url){
        return IM_SERVER_PREFIX + url;
    }

}
