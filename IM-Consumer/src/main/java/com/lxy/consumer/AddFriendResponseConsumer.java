package com.lxy.consumer;

import com.lxy.application.OfflineMsgService;
import com.lxy.domain.user.model.UserInfo;
import com.lxy.domain.user.service.UserServiceImpl;
import com.lxy.protocolpackage.mq.MessageConsumerGroup;
import com.lxy.protocolpackage.mq.MessageTag;
import com.lxy.protocolpackage.mq.MessageTopic;
import com.lxy.protocolpackage.protocol.friend.AddFriendRequest;
import com.lxy.protocolpackage.protocol.friend.AddFriendResponse;
import com.lxy.protocolpackage.protocol.friend.FriendRequest;
import com.lxy.protocolpackage.protocol.friend.FriendResponse;
import com.lxy.protocolpackage.rediskey.ImServerKey;
import com.lxy.protocolpackage.rpc.AddFriendRequestRPC;
import com.lxy.protocolpackage.rpc.FriendResponseRPC;
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
        consumerGroup = MessageConsumerGroup.ADD_FRIEND_RESPONSE_CONSUMER_GROUP,  //消费组
        selectorExpression = MessageTag.ADD_FRIEND_RESPONSE)// 监听的tag
public class AddFriendResponseConsumer implements RocketMQListener<AddFriendResponse> {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private OfflineMsgService offlineMsgService;

    @DubboReference
    private FriendResponseRPC friendResponseRPC;

    @Override
    public void onMessage(AddFriendResponse addFriendResponse) {
        if(addFriendResponse.getAgree()){
            // 创建消息
            String userId = addFriendResponse.getUserId();
            UserInfo userInfo = userService.queryUserInfo(userId);

            FriendResponse friendResponse = new FriendResponse();
            friendResponse.setAgreeFriendId(userInfo.getUserId());
            friendResponse.setAgreeFriendHead(userInfo.getUserHead());
            friendResponse.setAgreeFriendNickName(userInfo.getUserNickname());

            // 好友关系落库
            userService.asyncAddUserFriend(addFriendResponse.getUserId(), addFriendResponse.getRequestFriendId());

            // 查看是否在线
            String dubboUrl = stringRedisTemplate.opsForValue().get(ImServerKey.buildUserIdKey(addFriendResponse.getRequestFriendId()));

            // 如果不在线，则缓存
            if(dubboUrl == null || !StringUtils.hasLength(dubboUrl)) {
                System.out.println("user" + addFriendResponse.getRequestFriendId() + "不在线");
                offlineMsgService.storeOfflineMsg(addFriendResponse.getRequestFriendId(), friendResponse);
                return;
            }

            RpcContext.getContext().set("ip", dubboUrl);

            // 否则转发
            friendResponseRPC.sendToUserWithFriendResponse(addFriendResponse.getRequestFriendId(), friendResponse);
        }


    }
}
