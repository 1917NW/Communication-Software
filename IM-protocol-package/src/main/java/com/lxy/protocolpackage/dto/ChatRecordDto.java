package com.lxy.protocolpackage.dto;

import com.lxy.protocolpackage.constants.MsgUserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRecordDto implements Serializable {

    private String talkId;          // 对话框ID
    private String userId;          // 用户ID[自己/好友]
    private String userNickName;    // 用户昵称[群组聊天]
    private String userHead;        // 用户头像[群组聊天]
    private Integer msgUserType = MsgUserType.MINE_MSG.getMsgTypeCode();    // 消息类型[1自己/2好友]
    private String msgContent;      // 消息内容
    private Integer msgType;        // 消息类型；0文字消息、1固定表情、2文件
    private Date msgDate;           // 消息时间


}
