package com.lxy.consumer;

import com.lxy.application.OfflineMsgService;
import com.lxy.domain.user.model.ChatRecordInfo;
import com.lxy.domain.user.service.UserServiceImpl;
import com.lxy.protocolpackage.constants.TalkType;
import com.lxy.protocolpackage.dto.GroupDto;
import com.lxy.protocolpackage.dto.UserDto;
import com.lxy.protocolpackage.mq.MessageConsumerGroup;
import com.lxy.protocolpackage.mq.MessageTag;
import com.lxy.protocolpackage.mq.MessageTopic;
import com.lxy.protocolpackage.protocol.group.MsgGroupRequest;
import com.lxy.protocolpackage.protocol.group.MsgGroupResponse;
import com.lxy.protocolpackage.protocol.msg.MsgRequest;
import com.lxy.protocolpackage.rediskey.ImServerKey;
import com.lxy.protocolpackage.rpc.GroupMsgRPC;
import com.lxy.threadPool.TaskExecutor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RocketMQMessageListener(topic = MessageTopic.IM_TOPIC,  // 主题
        consumerGroup = MessageConsumerGroup.GROUP_CHAT_CONSUMER_GROUP,  //消费者组
        selectorExpression = MessageTag.GROUP_CHAT_TAG)// 监听的tag
public class GroupMsgConsumer implements RocketMQListener<MsgGroupRequest> {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private OfflineMsgService offlineMsgService;

    @DubboReference
    private GroupMsgRPC groupMsgRPC;

    @Override
    public void onMessage(MsgGroupRequest msgGroupRequest) {
        String groupId = msgGroupRequest.getGroupId();

        // 获取所有在线用户所在的服务器url和不在线用户的userId
        List<String> userIdListByGroupId = userService.getUserIdListByGroupId(groupId);
        List<String> offlineUserIdList = new ArrayList<>();
        Set<String> onlineUserIdDubboUrlList = new HashSet<>();

        if(userIdListByGroupId != null && !userIdListByGroupId.isEmpty()){
            for(String userId : userIdListByGroupId){
                String dubboUrl = stringRedisTemplate.opsForValue().get(ImServerKey.buildUserIdKey(userId));
                if(dubboUrl == null || !StringUtils.hasLength(dubboUrl))
                    offlineUserIdList.add(userId);
                else
                    onlineUserIdDubboUrlList.add(dubboUrl);
            }
        }

        // 异步消息记录写库
        userService.asyncAppendChatRecord(new ChatRecordInfo(msgGroupRequest.getUserDto().getUserId(), msgGroupRequest.getGroupId(), msgGroupRequest.getMsgText(), msgGroupRequest.getMsgType(), msgGroupRequest.getMsgDate(), TalkType.GROUP_MESSAGE.getTalkTypeCode()));


        // 群发消息
        GroupDto groupDto = userService.queryGroupInfo(msgGroupRequest.getGroupId());
        MsgGroupResponse msgGroupResponse = new MsgGroupResponse();
        UserDto userDto = msgGroupRequest.getUserDto();

        msgGroupResponse.setGroupId(msgGroupRequest.getGroupId());
        msgGroupResponse.setGroupName(groupDto.getGroupName());
        msgGroupResponse.setGroupHead(groupDto.getGroupHead());

        msgGroupResponse.setUserId(userDto.getUserId());
        msgGroupResponse.setUserHead(userDto.getUserHead());
        msgGroupResponse.setUserNickName(userDto.getUserNickName());
        msgGroupResponse.setMsg(msgGroupRequest.getMsgText());
        msgGroupResponse.setMsgType(msgGroupRequest.getMsgType());
        msgGroupResponse.setMsgDate(msgGroupRequest.getMsgDate());

        // 异步缓存
        TaskExecutor.execTask(()->{
            offlineMsgService.storeBatchOfflineGroupMsg(offlineUserIdList, msgGroupResponse);
        });

        // 向在线的服务器发送请求
        for (String dubboUrl : onlineUserIdDubboUrlList){
            RpcContext.getContext().set("ip", dubboUrl);
            groupMsgRPC.sendToGroup(groupId, msgGroupResponse);
        }
    }
}
