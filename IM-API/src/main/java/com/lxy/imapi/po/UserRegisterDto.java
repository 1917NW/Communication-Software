package com.lxy.imapi.po;

import lombok.Data;

@Data
public class UserRegisterDto {
    String userPhone;
    String userNickname;
    String userPassword;

    String verifyCode;

}
