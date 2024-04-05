package com.lxy.socket;

import com.lxy.application.OfflineMsgService;
import com.lxy.infrastructure.common.UserOffineMsgCache;
import com.lxy.protocolpackage.protocol.Packet;
import com.lxy.socket.util.ServerIdGenerator;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Data
@Configuration
public class NettyServer {

    @Autowired
    private Environment environment;

    @Autowired
    private IMServerChannelInitializer imServerChannelInitializer;

    @Autowired
    private OfflineMsgService offlineMsgService;

    @Autowired
    private ServerIdGenerator serverIdGenerator;

    private static Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);
    private int port = 7397;

    public static String serverId;
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

        // 获取服务器id
        initServerId();



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

    @PreDestroy
    private void storeOfflineMsg() {
        Map<String, List<Packet>> userOfflineMap = UserOffineMsgCache.getUserOfflineMap();

        Set<Map.Entry<String, List<Packet>>> entries = userOfflineMap.entrySet();
        for(Map.Entry<String, List<Packet>> entry : entries){
            offlineMsgService.storeBatchOfflineMsg(serverId, entry.getKey(), entry.getValue());
        }
    }

    private void initServerId() {
        // 从文件中读取
        String propertyFilePath = "src/main/resources/configuration.properties";

        Properties prop = new Properties();
        try(InputStream input = new FileInputStream(propertyFilePath)){
            prop.load(input);
            String serverIdStr = prop.getProperty("serverId");
            if(serverIdStr != null)
                serverId = serverIdStr;
            else{
                // 如果不存在，则从redis中获取并写入到文件中
                serverId = serverIdGenerator.nextId();
                prop.setProperty("serverId", serverId);
                try(FileOutputStream output = new FileOutputStream(propertyFilePath)){
                    prop.store(output, null);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

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
