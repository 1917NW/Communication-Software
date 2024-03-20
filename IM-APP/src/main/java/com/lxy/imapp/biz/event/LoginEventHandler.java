package com.lxy.imapp.biz.event;

import com.lxy.imapp.biz.util.BeanUtil;
import com.lxy.imapp.biz.util.CacheUtil;
import com.lxy.imapp.front.ImUI;
import com.lxy.protocolpackage.protocol.login.LoginRequest;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginEventHandler {



    private Logger logger = LoggerFactory.getLogger(LoginEventHandler.class);


    public void doLogin(String userId, String userPassword) {
        Channel channel = BeanUtil.getBean("channel", Channel.class);
        channel.writeAndFlush(new LoginRequest(userId, userPassword));
        CacheUtil.userId = userId;
    }

}