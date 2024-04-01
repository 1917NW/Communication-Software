package com.lxy.protocolpackage.constants;

/**
 * 消息类型
 * 0 文字消息
 * 1 表情
 * 2 文件
 */
public enum MsgType {
    // 自己的消息
    TEXT_MSG(0),
    // 其他人的消息
    EMOTION_MSG(1),
    FILE_MSG(2);

    private Integer msgKindCode;
    MsgType(Integer msgTypeCode) {
        this.msgKindCode = msgTypeCode;
    }

    public Integer getMsgTypeCode(){
        return this.msgKindCode;
    }
}
