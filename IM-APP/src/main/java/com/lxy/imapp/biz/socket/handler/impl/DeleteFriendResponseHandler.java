package com.lxy.imapp.biz.socket.handler.impl;

import com.lxy.imapp.biz.socket.handler.AbstractBizHandler;
import com.lxy.imapp.front.cache.ParentNodeCache;
import com.lxy.protocolpackage.protocol.friend.AddFriendRequest;
import com.lxy.protocolpackage.protocol.friend.DeleteFriendResponse;
import io.netty.channel.Channel;

public class DeleteFriendResponseHandler extends AbstractBizHandler<DeleteFriendResponse> {
    @Override
    public void channelRead(Channel channel, DeleteFriendResponse msg) {
        ParentNodeCache.removeFriend(msg.getDeletedUserId());
    }
}
