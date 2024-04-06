package com.lxy.socket.handler.impl.group;

import cn.hutool.json.JSONUtil;
import com.lxy.application.UserService;
import com.lxy.domain.user.model.ChatRecordInfo;
import com.lxy.infrastructure.common.SocketChannelUtil;
import com.lxy.infrastructure.common.UserOffineMsgCache;
import com.lxy.protocolpackage.constants.TalkType;
import com.lxy.protocolpackage.dto.GroupDto;
import com.lxy.protocolpackage.dto.UserDto;
import com.lxy.protocolpackage.mq.MessageTopic;
import com.lxy.protocolpackage.protocol.group.MsgGroupRequest;
import com.lxy.protocolpackage.protocol.group.MsgGroupResponse;
import com.lxy.socket.handler.AbstractBizHandler;
import com.lxy.threadPool.TaskExecutor;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.group.ChannelGroup;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ChannelHandler.Sharable
public class MsgGroupRequestHandler extends AbstractBizHandler<MsgGroupRequest> {

    @Autowired
    private UserService userService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    @Override
    public void channelRead(Channel channel, MsgGroupRequest msg) {
        ChannelGroup channelGroup = SocketChannelUtil.getChannelGroupById(msg.getGroupId());

        // 第一次创建群组发送消息后会进入该代码快
        if(channelGroup == null){
            SocketChannelUtil.addChannelGroup(msg.getGroupId(), channel);
            channelGroup = SocketChannelUtil.getChannelGroupById(msg.getGroupId());
        }

        // 群组内的所有人都会添加一个会话
        TaskExecutor.execTask(() -> {
            // 获取某个组内的所有用户id
            List<String> userIdListByGroupId = userService.getUserIdListByGroupId(msg.getGroupId());
            userService.addTalkBoxInfoBatch(userIdListByGroupId, msg.getGroupId(), TalkType.GROUP_MESSAGE.getTalkTypeCode());
        });

        rocketMQTemplate.send(MessageTopic.getGroupChatTag(), MessageBuilder.withPayload(msg).build());





    }
}
