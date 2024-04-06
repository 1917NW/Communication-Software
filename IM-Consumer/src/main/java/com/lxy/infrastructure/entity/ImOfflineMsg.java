package com.lxy.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (ImOfflineMsg)表实体类
 *
 * @author makejava
 * @since 2024-04-02 15:57:35
 */
@TableName("im_offline_msg")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImOfflineMsg{

    
     @TableId(type = IdType.AUTO)
     private Integer id;

    //接收者id
     private String recipientId;
    //接收的消息体的json串
     private String packetJsonStr;

     private Byte packetType;






}

