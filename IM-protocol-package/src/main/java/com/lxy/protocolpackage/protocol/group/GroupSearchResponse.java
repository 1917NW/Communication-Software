package com.lxy.protocolpackage.protocol.group;

import com.lxy.protocolpackage.dto.GroupDto;
import com.lxy.protocolpackage.protocol.Command;
import com.lxy.protocolpackage.protocol.Packet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupSearchResponse extends Packet {
    List<GroupDto> groupDtoList;

    @Override
    public Byte getCommand() {
        return Command.SearchGroupResponse;
    }
}
