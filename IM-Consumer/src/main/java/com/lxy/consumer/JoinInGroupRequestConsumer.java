package com.lxy.consumer;

import cn.hutool.json.JSONUtil;
import com.lxy.application.OfflineMsgService;
import com.lxy.domain.user.model.UserInfo;
import com.lxy.domain.user.service.UserServiceImpl;
import com.lxy.protocolpackage.dto.GroupDto;
import com.lxy.protocolpackage.dto.UserDto;
import com.lxy.protocolpackage.mq.MessageConsumerGroup;
import com.lxy.protocolpackage.mq.MessageTag;
import com.lxy.protocolpackage.mq.MessageTopic;
import com.lxy.protocolpackage.protocol.friend.FriendRequest;
import com.lxy.protocolpackage.protocol.group.FullUserJoinInGroupRequest;
import com.lxy.protocolpackage.protocol.group.JoinInGroupRequest;
import com.lxy.protocolpackage.rediskey.ImServerKey;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RocketMQMessageListener(topic = MessageTopic.IM_TOPIC,  // 主题
        consumerGroup = MessageConsumerGroup.GROUP_REQUEST_CONSUMER_GROUP,  //消费组
        selectorExpression = MessageTag.GROUP_REQUEST)// 监听的tag
public class JoinInGroupRequestConsumer implements RocketMQListener<JoinInGroupRequest> {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private OfflineMsgService offlineMsgService;

    @Override
    public void onMessage(JoinInGroupRequest joinInGroupRequest) {
        // 创建消息
        System.out.println("收到申请加入群组消息:"+ JSONUtil.toJsonStr(joinInGroupRequest));
        UserInfo userInfo = userService.queryUserInfo(joinInGroupRequest.getUserId());
        UserDto userDto = new UserDto();
        userDto.setUserHead(userInfo.getUserHead());
        userDto.setUserId(userInfo.getUserId());
        userDto.setUserNickName(userInfo.getUserNickname());

        GroupDto groupDto = userService.queryGroupInfo(joinInGroupRequest.getGroupId());

        FullUserJoinInGroupRequest fullUserJoinInGroupRequest = new FullUserJoinInGroupRequest();
        fullUserJoinInGroupRequest.setUserDto(userDto);
        fullUserJoinInGroupRequest.setGroupDto(groupDto);

        // 查看是否在线
        String dubboUrl = stringRedisTemplate.opsForValue().get(ImServerKey.buildUserIdKey(joinInGroupRequest.getLeaderId()));


        // 如果不在线，则缓存
        if(dubboUrl == null || !StringUtils.hasLength(dubboUrl)) {
            System.out.println("user" + joinInGroupRequest.getLeaderId() + "不在线");
            offlineMsgService.storeOfflineMsg(joinInGroupRequest.getLeaderId(), fullUserJoinInGroupRequest);
            return;
        }



    }
}
