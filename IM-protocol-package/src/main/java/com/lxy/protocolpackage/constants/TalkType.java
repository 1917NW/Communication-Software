package com.lxy.protocolpackage.constants;

public enum TalkType {
    // 好友消息
    PRIVATE_MESSAGE(1),

    // 群组消息
    GROUP_MESSAGE(2)
    ;


    private Integer talkTypeCode;

    TalkType(int talkTypeCode) {
        this.talkTypeCode = talkTypeCode;
    }

    public Integer getTalkTypeCode() {
        return talkTypeCode;
    }
}
