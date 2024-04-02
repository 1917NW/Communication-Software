package com.lxy.imapi.service;

import com.lxy.imapi.po.UserRegisterDto;
import com.lxy.imapi.po.UserRegisterResult;


public interface RegisterService {
    public UserRegisterResult register(UserRegisterDto userRegisterDto);

    public void getCode(String userPhone);

}
