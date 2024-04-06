package com.lxy.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRecordInfo {

    private String userId;     // 用户ID
    private String friendId;   // 好友ID
    private String msgContent; // 消息内容
    private Integer msgType;   // 消息类型；0文字消息、1固定表情、2文件消息
    private Date msgDate;      // 消息时间
    private Integer talkType;  // 对话框类型；0好友、1群组
}