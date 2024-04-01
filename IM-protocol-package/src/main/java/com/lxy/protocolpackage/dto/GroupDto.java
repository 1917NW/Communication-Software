package com.lxy.protocolpackage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupDto implements Serializable {
    private String groupId;       // 群组ID
    private String groupName; // 群组名称
    private String groupHead;     // 群组头像

    private String groupLeaderId; // 群主
    private Integer status;  // 群组状态

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupDto groupDto = (GroupDto) o;
        return Objects.equals(groupId, groupDto.groupId) && Objects.equals(groupName, groupDto.groupName) && Objects.equals(groupHead, groupDto.groupHead) && Objects.equals(groupLeaderId, groupDto.groupLeaderId) && Objects.equals(status, groupDto.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, groupName, groupHead, groupLeaderId, status);
    }
}
