package com.lxy.imapi.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterResult {

    public static String VERIFICATION_CODE_EXPIRED = "验证码过期，请重新执行注册流程";

    public static String VERIFICATION_CODE_NOT_MATCH = "验证码不匹配";

    public static String VERIFICATION_CODE_MATCH = "验证码匹配，注册成功";

    private boolean success;
    private String remindMsg;
}
