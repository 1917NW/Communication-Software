package com.lxy.infrastructure.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (ImGroup)表实体类
 *
 * @author makejava
 * @since 2024-03-19 16:13:23
 */
@TableName("im_group")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImGroup{
    //自增ID
    private Long id;
    //群组ID
    private String groupId;
    //群组名称
    private String groupName;
    //群组头像
    private String groupHead;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;



    
    }

