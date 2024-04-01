package com.lxy.imapp.biz.http;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.httpclient.NameValuePair;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegisterURL {
    private static String GET_VERIFY_CODE_URL = "http://localhost:8888/imSystem/getCode";

    private static String VERIFY_URL = "http://localhost:8888/imSystem/register";

    public static UserRegisterResult verifyCode(UserRegisterDto userRegisterDto){
        try {
            String result = HttpClientHelper.sendPost(VERIFY_URL, userRegisterDto);
            UserRegisterResult userRegisterResult = JSONUtil.toBean(result, UserRegisterResult.class);
            return userRegisterResult;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getVerifyCode(String userPhone){
        try {
            Map<String,String> requestParams = new HashMap<>();
            requestParams.put("userPhone", userPhone);
            HttpClientHelper.sendGet(GET_VERIFY_CODE_URL, requestParams);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
