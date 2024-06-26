package com.lxy.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupInfo {
    private String userId;       //用户ID
    private String userNickName; //用户昵称
    private String userHead;     //用户头像
}