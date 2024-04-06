package com.lxy.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

;

/**
 * (ImUserTalk)表实体类
 *
 * @author makejava
 * @since 2024-03-19 16:26:10
 */
@TableName("im_user_talk")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImUserTalk{
    //自增ID
    private Long id;
    //用户ID
    private String userId;
    //对话框ID(可以是好友ID或者群组ID)
    private String talkId;
    //对话框类型：0为好友对话，1为群组对话
    private Integer talkType;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;


    }

