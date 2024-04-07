package com.lxy.socket.handler.impl.group;

import cn.hutool.json.JSONUtil;
import com.lxy.application.UserService;
import com.lxy.domain.user.model.UserInfo;
import com.lxy.infrastructure.common.SocketChannelUtil;
import com.lxy.infrastructure.common.UserOffineMsgCache;
import com.lxy.protocolpackage.dto.GroupDto;
import com.lxy.protocolpackage.dto.UserDto;
import com.lxy.protocolpackage.mq.MessageTopic;
import com.lxy.protocolpackage.protocol.group.FullUserJoinInGroupRequest;
import com.lxy.protocolpackage.protocol.group.JoinInGroupRequest;
import com.lxy.socket.handler.AbstractBizHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class JoinInGroupRequestHandler extends AbstractBizHandler<JoinInGroupRequest> {
    @Autowired
    private UserService userService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    
    @Override
    public void channelRead(Channel channel, JoinInGroupRequest msg) {

        Channel groupLeaderChannel = SocketChannelUtil.getChannel(msg.getLeaderId());
        if(groupLeaderChannel == null){
            rocketMQTemplate.send(MessageTopic.getGroupRequestTag(), MessageBuilder.withPayload(msg).build());
            return;
        }


        System.out.println("收到申请加入群组消息:"+ JSONUtil.toJsonStr(msg));
        UserInfo userInfo = userService.queryUserInfo(msg.getUserId());
        UserDto userDto = new UserDto();
        userDto.setUserHead(userInfo.getUserHead());
        userDto.setUserId(userInfo.getUserId());
        userDto.setUserNickName(userInfo.getUserNickname());

        GroupDto groupDto = userService.queryGroupInfo(msg.getGroupId());

        FullUserJoinInGroupRequest fullUserJoinInGroupRequest = new FullUserJoinInGroupRequest();
        fullUserJoinInGroupRequest.setUserDto(userDto);
        fullUserJoinInGroupRequest.setGroupDto(groupDto);



        groupLeaderChannel.writeAndFlush(fullUserJoinInGroupRequest);


    }
}
