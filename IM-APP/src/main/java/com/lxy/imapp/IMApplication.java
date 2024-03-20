package com.lxy.imapp;

import com.lxy.imapp.biz.event.ChatEventHandler;
import com.lxy.imapp.biz.event.LoginEventHandler;
import com.lxy.imapp.biz.socket.NettyClient;
import com.lxy.imapp.front.ImUI;
import com.lxy.imapp.front.view.Chat;
import com.lxy.imapp.front.view.Login;
import com.lxy.protocolpackage.protocol.login.LoginRequest;
import com.lxy.protocolpackage.util.SerializationUtil;
import io.netty.channel.Channel;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class IMApplication extends Application {

    private Logger logger = LoggerFactory.getLogger(Application.class);

    //默认线程池
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);
    @Override
    public void start(Stage stage) throws Exception {
        // 1. 初始化窗口
        Chat chat = new Chat(new ChatEventHandler());
        Login login = new Login(new LoginEventHandler(), chat);

        ImUI imUI = new ImUI();
        imUI.setChat(chat);
        imUI.setLogin(login);
        login.show();

        // 2. 初始化链接
        NettyClient nettyClient = new NettyClient(imUI);
        Future<Channel> future = executorService.submit(nettyClient);
        Channel channel = future.get();
        if(channel == null)
            throw new RuntimeException("netty server connect error!");

        while (!nettyClient.isActive()) {
            logger.info("NettyClient启动服务 ...");
            Thread.sleep(500);
        }
        logger.info("NettyClient连接服务完成 {}", channel.localAddress());


    }

}