package com.lxy.infrastructure.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

;

/**
 * (ImUserFriend)表实体类
 *
 * @author makejava
 * @since 2024-03-19 16:25:23
 */
@TableName("im_user_friend")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImUserFriend{
    @TableId(type = IdType.AUTO)
    //自增ID
    private Long id;
    //用户ID
    private String userId;
    //好友用户ID
    private String userFriendId;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    public ImUserFriend(String userId, String userFriendId){
        this.userId = userId;
        this.userFriendId = userFriendId;
        this.createTime = this.updateTime = DateTime.now();
    }



    
    }

