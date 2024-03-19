package com.lxy.imapp.biz.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.channels.Channel;
import java.util.concurrent.Callable;


public class NettyClient implements Callable<Channel> {

    private Logger logger = LoggerFactory.getLogger(NettyClient.class);

    private String inetHost = "127.0.0.1";

    private int inetPort = 7397;

    @Override
    public Channel call() throws Exception {

        return null;
    }
}
