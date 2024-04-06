package com.lxy.consumer;

import com.lxy.application.OfflineMsgService;
import com.lxy.domain.user.model.ChatRecordInfo;
import com.lxy.domain.user.service.UserServiceImpl;
import com.lxy.protocolpackage.constants.TalkType;
import com.lxy.protocolpackage.dto.UserDto;
import com.lxy.protocolpackage.mq.MessageConsumerGroup;
import com.lxy.protocolpackage.mq.MessageTag;
import com.lxy.protocolpackage.mq.MessageTopic;
import com.lxy.protocolpackage.protocol.msg.MsgRequest;
import com.lxy.protocolpackage.protocol.msg.MsgResponse;
import com.lxy.protocolpackage.rediskey.ImServerKey;
import com.lxy.protocolpackage.rpc.PrivateMsgRPC;
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
        consumerGroup = MessageConsumerGroup.CHAT_CONSUMER_GROUP,  //消费者组
        selectorExpression = MessageTag.CHAT_TAG)// 监听的tag
public class MsgRequestConsumer implements RocketMQListener<MsgRequest> {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private OfflineMsgService offlineMsgService;

    @DubboReference
    private PrivateMsgRPC privateMsgRPC;

    @Override
    public void onMessage(MsgRequest msg) {
        System.out.println("收到消息服务器的消息，开始消费");
        UserDto sender = msg.getUserDto();
        userService.asyncAppendChatRecord(new ChatRecordInfo(sender.getUserId(), msg.getFriendId(), msg.getMsgText(), msg.getMsgType(),msg.getMsgDate(), TalkType.PRIVATE_MESSAGE.getTalkTypeCode()));

        // 接收方talk数据
        userService.addTalkBoxInfo(msg.getFriendId(), sender.getUserId(), TalkType.PRIVATE_MESSAGE.getTalkTypeCode());

        // 组装消息
        MsgResponse msgResponse = new MsgResponse();
        msgResponse.setMsgDate(msg.getMsgDate());
        msgResponse.setMsgText(msg.getMsgText());
        msgResponse.setMsgType(msg.getMsgType());
        msgResponse.setUserDto(sender);

        // 查看是否在线
        String dubboUrl = stringRedisTemplate.opsForValue().get(ImServerKey.buildUserIdKey(msg.getFriendId()));


        // 如果不在线，则缓存
        if(dubboUrl == null || !StringUtils.hasLength(dubboUrl)) {
            System.out.println("user" + msg.getFriendId() + "不在线");
            offlineMsgService.storeOfflineMsg(msg.getFriendId(), msgResponse);
            return;
        }

        RpcContext.getContext().set("ip", dubboUrl);
        // 否则转发
        privateMsgRPC.sendToUser(msg.getFriendId(), msgResponse);
    }
}
