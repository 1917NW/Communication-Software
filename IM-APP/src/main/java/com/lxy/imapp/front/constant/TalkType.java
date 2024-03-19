package com.lxy.imapp.front.constant;

public enum TalkType {
    PRIVATE_MESSAGE(1),
    GROUP_MESSAGE(2)
    ;


    private int talkTypeCode;

    TalkType(int talkTypeCode) {
        this.talkTypeCode = talkTypeCode;
    }

    public int getTalkTypeCode() {
        return talkTypeCode;
    }
}
