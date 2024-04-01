package com.lxy.protocolpackage.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 朋友状态
 * 没有加入
 * 已经加入
 */
@Getter
@AllArgsConstructor
public enum FriendState {
    NOT_ADD(0),
    RESRVED(1),
    HAVE_ADDED(2);
    private Integer stateCode;

}
