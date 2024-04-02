package com.lxy.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

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

     // 服务器编号
     private String serverId;




}

