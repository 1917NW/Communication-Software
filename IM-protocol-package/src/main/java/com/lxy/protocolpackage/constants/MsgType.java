package com.lxy.protocolpackage.constants;

public enum MsgType {
    // 自己的消息
    MINE_MSG(1),
    // 其他人的消息
    OTHERS_MSG(2);

    private Integer msgTypeCode;
    MsgType(Integer msgTypeCode) {
        this.msgTypeCode = msgTypeCode;
    }

    public Integer getMsgTypeCode(){
        return this.msgTypeCode;
    }

}
