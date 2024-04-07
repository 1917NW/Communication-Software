package com.lxy.consumer;

import com.lxy.application.OfflineMsgService;
import com.lxy.domain.user.service.UserServiceImpl;
import com.lxy.protocolpackage.mq.MessageConsumerGroup;
import com.lxy.protocolpackage.mq.MessageTag;
import com.lxy.protocolpackage.mq.MessageTopic;
import com.lxy.protocolpackage.protocol.group.FullGroupJoinInGroupResponse;
import com.lxy.protocolpackage.protocol.group.JoinInGroupResponse;
import com.lxy.protocolpackage.rediskey.ImServerKey;
import com.lxy.protocolpackage.rpc.JoinInGroupResponseRPC;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RocketMQMessageListener(topic = MessageTopic.IM_TOPIC,  // 主题
        consumerGroup = MessageConsumerGroup.FULL_GROUP_JOIN_In_GROUP_RESPONSE_CONSUMER_GROUP,  //消费组
        selectorExpression = MessageTag.FULL_GROUP_JOIN_In_GROUP_RESPONSE)// 监听的tag
public class FullGroupJoinInGroupResponseConsumer  implements RocketMQListener<FullGroupJoinInGroupResponse> {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private OfflineMsgService offlineMsgService;

    @DubboReference
    private JoinInGroupResponseRPC joinInGroupResponseRPC;

    @Override
    public void onMessage(FullGroupJoinInGroupResponse fullGroupJoinInGroupResponse) {
        if(fullGroupJoinInGroupResponse.getAgree()) {
            // 加入群组
            userService.addUserToGroup(fullGroupJoinInGroupResponse.getUserId(), fullGroupJoinInGroupResponse.getGroupDto().getGroupId());

            // 创建消息
            JoinInGroupResponse joinInGroupResponse = new JoinInGroupResponse();
            joinInGroupResponse.setGroupDto(fullGroupJoinInGroupResponse.getGroupDto());

            // 查看是否在线
            String dubboUrl = stringRedisTemplate.opsForValue().get(ImServerKey.buildUserIdKey(fullGroupJoinInGroupResponse.getUserId()));

            // 如果不在线，则缓存
            if(dubboUrl == null || !StringUtils.hasLength(dubboUrl)) {
                System.out.println("user" + fullGroupJoinInGroupResponse.getUserId() + "不在线");
                offlineMsgService.storeOfflineMsg(fullGroupJoinInGroupResponse.getUserId(), joinInGroupResponse);
                return;
            }

            RpcContext.getContext().set("ip", dubboUrl);
            joinInGroupResponseRPC.sendToUserWithJoinInGroupResponse(fullGroupJoinInGroupResponse.getUserId(), joinInGroupResponse);


        }
    }
}
