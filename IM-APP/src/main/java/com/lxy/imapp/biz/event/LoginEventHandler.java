package com.lxy.imapp.biz.event;

import com.lxy.imapp.biz.util.BeanUtil;
import com.lxy.protocolpackage.protocol.login.LoginRequest;
import com.lxy.protocolpackage.protocol.register.RegisterSuccessRequest;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginEventHandler {



    private Logger logger = LoggerFactory.getLogger(LoginEventHandler.class);


    public void doLogin(String userId, String userPassword) {
        Channel channel = BeanUtil.getChannel();
        channel.writeAndFlush(new LoginRequest(userId, userPassword));
    }

    public void doRegister(String userId){
        Channel channel = BeanUtil.getChannel();
        channel.writeAndFlush(new RegisterSuccessRequest(userId));
    }

}