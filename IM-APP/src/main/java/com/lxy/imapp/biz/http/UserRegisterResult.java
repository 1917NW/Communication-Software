package com.lxy.imapp.biz.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterResult {



    private boolean success;
    private String remindMsg;
}
