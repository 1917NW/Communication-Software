package com.lxy.imapi.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResult {

    public static String USER_NOT_EXIST = "用户不存在";

    public static String USER_ACCOUNT_DO_NOT_MATCH_PASSWORD = "用户账号和密码不匹配";

    public static String USER_ACCOUNT_EMPTY = "用户账号为空";

    public static String USER_PASSWORD_EMPTY = "用户密码为空";


    private boolean success;

    private String remindMsg;
    private String imServerIp;

    private Integer imServerPort;
}
