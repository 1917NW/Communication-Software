package com.lxy.imapp.biz.http;

import cn.hutool.json.JSONUtil;
import com.lxy.protocolpackage.protocol.login.LoginRequest;

import java.io.IOException;

public class LoginURL {

    private static String LOGIN_URL = ApiURL.getApiUrL() +  "/imSystem/login";

    public static UserLoginResult doLogin(UserLoginDto userLoginDto){
        try {
            String result = HttpClientHelper.sendPost(LOGIN_URL, userLoginDto);
            UserLoginResult userLoginResult = JSONUtil.toBean(result, UserLoginResult.class);
            return userLoginResult;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
