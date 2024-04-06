package com.lxy.socket.cache;

public class ImServerCache {

    private static String imServerUrl;

    private static String dubboServiceUrl;

    public static void setImServerUrl(String imServerUrlOut){
        imServerUrl = imServerUrlOut;
    }

    public static String getImServerUrl(){
        return imServerUrl;
    }

    public static void setDubboServiceUrl(String dubboServiceUrlOut){
        dubboServiceUrl = dubboServiceUrlOut;
    }

    public static String getDubboServiceUrl(){
        return dubboServiceUrl;
    }

}
