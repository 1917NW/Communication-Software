package com.lxy.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

;

/**
 * (ImUserGroup)表实体类
 *
 * @author makejava
 * @since 2024-03-19 16:25:45
 */
@TableName("im_user_group")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImUserGroup{
    //自增ID
    private Long id;
    //用户ID
    private String userId;
    //群组ID
    private String groupId;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;



    
    }

