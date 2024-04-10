package com.lxy.imapi.service;

import com.lxy.imapi.po.UserLoginDto;
import com.lxy.imapi.po.UserLoginResult;

public interface LoginService {

    public UserLoginResult login(UserLoginDto userLoginDto);
}
