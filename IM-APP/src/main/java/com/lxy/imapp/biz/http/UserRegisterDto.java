package com.lxy.imapp.biz.http;

import lombok.Data;

@Data
public class UserRegisterDto {
    String userPhone;
    String userNickname;
    String userPassword;

    String verifyCode;



}
