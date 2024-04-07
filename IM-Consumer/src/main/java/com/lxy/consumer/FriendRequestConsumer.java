package com.lxy.consumer;

import com.lxy.application.OfflineMsgService;
import com.lxy.domain.user.model.UserInfo;
import com.lxy.domain.user.service.UserServiceImpl;
import com.lxy.protocolpackage.mq.MessageConsumerGroup;
import com.lxy.protocolpackage.mq.MessageTag;
import com.lxy.protocolpackage.mq.MessageTopic;
import com.lxy.protocolpackage.protocol.friend.AddFriendRequest;
import com.lxy.protocolpackage.protocol.friend.FriendRequest;
import com.lxy.protocolpackage.protocol.group.MsgGroupRequest;
import com.lxy.protocolpackage.rediskey.ImServerKey;
import com.lxy.protocolpackage.rpc.AddFriendRequestRPC;
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
        consumerGroup = MessageConsumerGroup.FRIEND_REQUEST_CONSUMER_GROUP,  //消费组
        selectorExpression = MessageTag.FRIEND_REQUEST)// 监听的tag
public class FriendRequestConsumer implements RocketMQListener<FriendRequest> {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private OfflineMsgService offlineMsgService;

    @DubboReference
    private AddFriendRequestRPC addFriendRequestRPC;

    @Override
    public void onMessage(FriendRequest friendRequest) {

        // 创建消息
        UserInfo userInfo = userService.queryUserInfo(friendRequest.getUserId());

        AddFriendRequest addFriendRequest = new AddFriendRequest();
        addFriendRequest.setRequestFriendId(userInfo.getUserId());
        addFriendRequest.setRequestFriendNickName(userInfo.getUserNickname());
        addFriendRequest.setRequestFriendHead(userInfo.getUserHead());

        // 查看是否在线
        String dubboUrl = stringRedisTemplate.opsForValue().get(ImServerKey.buildUserIdKey(friendRequest.getFriendId()));


        // 如果不在线，则缓存
        if(dubboUrl == null || !StringUtils.hasLength(dubboUrl)) {
            System.out.println("user" + friendRequest.getFriendId() + "不在线");
            offlineMsgService.storeOfflineMsg(friendRequest.getFriendId(), addFriendRequest);
            return;
        }

        RpcContext.getContext().set("ip", dubboUrl);

        // 否则转发
        addFriendRequestRPC.sendToUserWithFriendRequest(friendRequest.getFriendId(), addFriendRequest);

    }
}
