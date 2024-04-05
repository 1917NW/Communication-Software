package com.lxy.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

;

/**
 * (ImUserMsg)表实体类
 *
 * @author makejava
 * @since 2024-03-19 16:25:57
 */
@TableName("im_user_msg")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImUserMsg{
    //自增ID
    @TableId(type = IdType.AUTO)
    private Long id;
    //自己ID
    private String userId;
    //对话ID
    private String talkId;
    //对话类型：0好友，1群组
    private Integer talkType;
    //消息内容
    private String msgContent;

    // 消息类型
    private Integer msgType;

    //消息时间
    private Date msgDate;



    
    }

