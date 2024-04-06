package com.lxy.imapp.biz.http;

public class ApiURL {
    public static String API_URI = "http://localhost";

    public static Integer API_PORT = 8889;

    public static String getApiUrL(){
        return API_URI + ":" + API_PORT;
    }

}
