package com.lxy.protocolpackage.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FriendState {
    NOT_ADD(0),
    RESRVED(1),
    HAVE_ADDED(2);
    private Integer stateCode;

}
