package com.lxy.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 新增的好友信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LuckUserInfo {

    private String userId;       //用户ID
    private String userNickName; //用户昵称
    private String userHead;     //用户头像
    private Integer status;      // 状态；0添加、1[保留]、2已添加
}
