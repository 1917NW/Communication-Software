package com.lxy.domain.server.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lxy.application.OfflineMsgService;
import com.lxy.infrastructure.dao.ImOfflineMsgDao;
import com.lxy.infrastructure.entity.ImOfflineMsg;
import com.lxy.protocolpackage.protocol.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * (ImOfflineMsg)表服务实现类
 *
 * @author makejava
 * @since 2024-04-02 16:40:42
 */
@Service("imOfflineMsgService")
public class ImOfflineMsgServiceImpl extends ServiceImpl<ImOfflineMsgDao, ImOfflineMsg> implements OfflineMsgService {

    @Autowired
    private ImOfflineMsgDao imOfflineMsgDao;

    @Override
    public void storeOfflineMsg(String userId, Packet packet) {
        ImOfflineMsg imOfflineMsg = new ImOfflineMsg();

        imOfflineMsg.setRecipientId(userId);
        imOfflineMsg.setPacketType(packet.getCommand());
        imOfflineMsg.setPacketJsonStr(JSONUtil.toJsonStr(packet));
        imOfflineMsgDao.insert(imOfflineMsg);
    }


    @Override
    public void storeBatchOfflineMsg(String userId, List<Packet> packetList) {

        if(packetList!=null && !packetList.isEmpty()){
            List<ImOfflineMsg> collect = packetList.stream().map(packet -> {
                ImOfflineMsg imOfflineMsg = new ImOfflineMsg();

                imOfflineMsg.setRecipientId(userId);
                imOfflineMsg.setPacketType(packet.getCommand());
                imOfflineMsg.setPacketJsonStr(JSONUtil.toJsonStr(packet));

                return imOfflineMsg;
            }).collect(Collectors.toList());

            this.saveBatch(collect);

        }
    }

    @Override
    public void storeBatchOfflineGroupMsg(List<String> userIdList, Packet packet){
        String packetJsonStr = JSONUtil.toJsonStr(packet);
        if(userIdList!=null && !userIdList.isEmpty()){

            List<ImOfflineMsg> collect = userIdList.stream().map( userId -> {
                ImOfflineMsg imOfflineMsg = new ImOfflineMsg();

                imOfflineMsg.setRecipientId(userId);
                imOfflineMsg.setPacketType(packet.getCommand());
                imOfflineMsg.setPacketJsonStr(packetJsonStr);
                return imOfflineMsg;
            }).collect(Collectors.toList());

            this.saveBatch(collect);

        }
    }





    @Override
    @Transactional
    public List<ImOfflineMsg> getOfflineMsgByUserId(String userId) {
        // 查询所有serverId的离线消息
        LambdaQueryWrapper<ImOfflineMsg> imOfflineMsgLambdaQueryWrapper = new LambdaQueryWrapper<>();
        imOfflineMsgLambdaQueryWrapper.eq(ImOfflineMsg::getRecipientId, userId);
        List<ImOfflineMsg> imOfflineMsgs = imOfflineMsgDao.selectList(imOfflineMsgLambdaQueryWrapper);

        // 删除所有serverId的离线消息
        imOfflineMsgDao.delete(imOfflineMsgLambdaQueryWrapper);


        return  imOfflineMsgs;
    }
}

