package com.lxy.infrastructure.entity;

;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;

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
    //自增ID
    private Long id;
    //用户ID
    private Long userId;
    //好友用户ID
    private Long userFriendId;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;



    
    }

