package com.lxy.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Data
@Configuration
public class NettyServer {

    @Autowired
    private Environment environment;

    @Autowired
    private IMServerChannelInitializer imServerChannelInitializer;

    private static Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);
    private int port = 7397;
    public void startApplication(int port) throws InterruptedException {
        setPort(port);
        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(8);
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.option(ChannelOption.MAX_MESSAGES_PER_WRITE, 100);
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.childHandler(imServerChannelInitializer);

        // 监听
        ChannelFuture channelFuture = bootstrap.bind(port).sync();
        System.out.println("netty服务启动成功，绑定端口：" + port);

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            System.out.println("安全销毁线程池");
        }));

        channelFuture.channel().closeFuture().sync();


    }



    @PostConstruct
    public void run(){
        new Thread(()->{
            try {
                startApplication(port);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }


}
