package com.lxy.consumer;

import com.lxy.domain.user.service.UserServiceImpl;
import com.lxy.protocolpackage.mq.MessageConsumerGroup;
import com.lxy.protocolpackage.mq.MessageTag;
import com.lxy.protocolpackage.mq.MessageTopic;
import com.lxy.protocolpackage.protocol.friend.AddFriendResponse;
import com.lxy.protocolpackage.protocol.friend.DeleteFriendRequest;
import com.lxy.protocolpackage.protocol.friend.DeleteFriendResponse;
import com.lxy.protocolpackage.rediskey.ImServerKey;
import com.lxy.protocolpackage.rpc.DeleteFriendResponseRPC;
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
        consumerGroup = MessageConsumerGroup.DELETE_FRIEND_REQUEST_CONSUMER_GROUP,  //消费组
        selectorExpression = MessageTag.DELETE_FRIEND_REQUEST)// 监听的tag
public class DeleteFriendRequestConsumer implements RocketMQListener<DeleteFriendRequest> {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @DubboReference
    private DeleteFriendResponseRPC deleteFriendResponseRPC;

    @Override
    public void onMessage(DeleteFriendRequest deleteFriendRequest) {
        String invokerId = deleteFriendRequest.getInvokerId();
        String deletedUserId = deleteFriendRequest.getDeletedUserId();

        userService.asyncDeleteUserFriend(deletedUserId, invokerId);

        // 查看是否在线
        String dubboUrl = stringRedisTemplate.opsForValue().get(ImServerKey.buildUserIdKey(deleteFriendRequest.getDeletedUserId()));

        // 如果不在线，则缓存
        if(dubboUrl == null || !StringUtils.hasLength(dubboUrl)) {
            System.out.println("user" + deleteFriendRequest.getDeletedUserId() + "不在线");
            return;
        }

        RpcContext.getContext().set("ip", dubboUrl);

        deleteFriendResponseRPC.sendToUser(deletedUserId, new DeleteFriendResponse(invokerId));


    }
}
