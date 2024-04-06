package com.lxy.imapp.biz.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImServerURL {
    private static String GET_IM_SERVER_URL = ApiURL.getApiUrL() + "/imServer/serverUrl";

    public static String getImServerURL(){
        Map<String,String> requestParams = new HashMap<>();
        String url = "";
        try {
            url = HttpClientHelper.sendGet(GET_IM_SERVER_URL, requestParams);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return url;
    }

    public static void main(String[] args) {
        System.out.println(getImServerURL());
    }
}
