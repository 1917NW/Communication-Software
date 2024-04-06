package com.lxy.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * (ImUser)表实体类
 *
 * @author makejava
 * @since 2024-03-19 16:22:26
 */
@TableName("im_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImUser{
    //自增ID
    @TableId(type = IdType.AUTO)
    private Long id;
    //用户ID
    private String userId;
    //用户昵称
    private String userNickname;
    //用户头像url，专门保存到OSS中
    private String userHead;
    //用户密码，加盐密文
    private String userPassword;
    //用户记录创建时间
    private Date createTime;
    //用户记录更新时间
    private Date updateTime;



    
    }

