package com.lxy.socket.handler.impl.friend;

import cn.hutool.json.JSONUtil;
import com.lxy.application.UserService;
import com.lxy.domain.user.model.LuckUserInfo;
import com.lxy.protocolpackage.dto.UserDto;
import com.lxy.protocolpackage.protocol.friend.SearchFriendRequest;
import com.lxy.protocolpackage.protocol.friend.SearchFriendResponse;
import com.lxy.socket.handler.AbstractBizHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ChannelHandler.Sharable
@Slf4j
public class SearchFriendHandler extends AbstractBizHandler<SearchFriendRequest> {


    @Autowired
    private UserService userService;

    @Override
    public void channelRead(Channel channel, SearchFriendRequest msg) {

        log.info("搜索好友请求处理:{}", JSONUtil.toJsonStr(msg));
        List<UserDto> userDtoList = new ArrayList<>();
        List<LuckUserInfo> userInfoList = userService.queryFuzzyUserInfoList(msg.getUserId(), msg.getSearchKey());

        if(userInfoList != null){
            for(LuckUserInfo userInfo : userInfoList){
                UserDto userDto = new UserDto();
                userDto.setUserId(userInfo.getUserId());
                userDto.setUserNickName(userInfo.getUserNickName());
                userDto.setUserHead(userInfo.getUserHead());
                userDto.setStatus(userInfo.getStatus());
                userDtoList.add(userDto);
            }
        }
        SearchFriendResponse response = new SearchFriendResponse();
        response.setList(userDtoList);
        channel.writeAndFlush(response);

    }
}