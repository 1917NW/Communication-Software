package com.lxy.imapp.biz.socket;

import com.lxy.imapp.biz.http.ImServerURL;
import com.lxy.imapp.biz.util.BeanUtil;
import com.lxy.imapp.front.ImUI;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.netty.channel.Channel;
import java.util.concurrent.Callable;


public class NettyClient implements Callable<Channel> {

    private Logger logger = LoggerFactory.getLogger(NettyClient.class);

    private String inetHost;

    private int inetPort ;

    private EventLoopGroup workerGroup = new NioEventLoopGroup();
    private Channel channel;

    public NettyClient(ImUI imUI) {
        this.imUI = imUI;
    }

    private ImUI imUI;


    @Override
    public Channel call() throws Exception {
        getServerIpAndPort();
        ChannelFuture channelFuture = null;
        try {
            Bootstrap client = new Bootstrap();
            client.group(workerGroup);
            client.channel(NioSocketChannel.class);
            client.option(ChannelOption.AUTO_READ, true);
            client.handler(new IMClientChannelInitializer(imUI));
            channelFuture = client.connect(inetHost, inetPort).syncUninterruptibly();
            this.channel = channelFuture.channel();
            BeanUtil.addBean("channel", channel);
        }catch (Exception e) {
            logger.error("socket client start error", e.getMessage());
        } finally {
            if (null != channelFuture && channelFuture.isSuccess()) {
                logger.info("socket client start done. ");
            } else {
                logger.error("socket client start error. ");
            }
        }
        return channel;
    }

    private void getServerIpAndPort() {
        String imServerURL = ImServerURL.getImServerURL();
        if(imServerURL != ""){
            String[] ipAndPort = imServerURL.split("-");
            inetHost = ipAndPort[0];
            inetPort = Integer.valueOf(ipAndPort[1]);
            System.out.println("inetHost:"+inetHost + ", inetPort:"+inetPort);
        }

    }

    public void destroy() {
        if (null == channel) return;
        channel.close();
        workerGroup.shutdownGracefully();
    }

    public boolean isActive(){
        return channel.isActive();
    }

    public Channel channel() {
        return channel;
    }
}
