package com.lxy.protocolpackage.protocol.group.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupDto {
    private String groupId;       // 群组ID
    private String groupName; // 群组名称
    private String groupHead;     // 群组头像

    private String groupLeaderId; // 群主
    private Integer status;  // 群组状态
}
