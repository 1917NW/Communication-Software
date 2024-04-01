package com.lxy.imapp.biz.socket.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewFriendRequest implements Serializable {
    String userId;

    String userName;
    String userHead;


}
