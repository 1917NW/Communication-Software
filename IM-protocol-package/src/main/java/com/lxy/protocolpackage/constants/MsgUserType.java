package com.lxy.protocolpackage.constants;

/**
 * 谁发送的消息
 */
public enum MsgUserType {
    // 自己的消息
    MINE_MSG(1),
    // 其他人的消息
    OTHERS_MSG(2);

    private Integer msgTypeCode;
    MsgUserType(Integer msgTypeCode) {
        this.msgTypeCode = msgTypeCode;
    }

    public Integer getMsgTypeCode(){
        return this.msgTypeCode;
    }

}
