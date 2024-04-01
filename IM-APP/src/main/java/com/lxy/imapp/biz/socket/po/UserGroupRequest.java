package com.lxy.imapp.biz.socket.po;


import com.lxy.protocolpackage.dto.GroupDto;
import com.lxy.protocolpackage.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupRequest implements Serializable {
    private UserDto userDto;
    private GroupDto groupDto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGroupRequest that = (UserGroupRequest) o;
        return Objects.equals(userDto, that.userDto) && Objects.equals(groupDto, that.groupDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userDto, groupDto);
    }
}
