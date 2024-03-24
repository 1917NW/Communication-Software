package com.lxy.socket;
import com.lxy.protocolpackage.codec.ObjDecoder;
import com.lxy.protocolpackage.codec.ObjEncoder;
import com.lxy.socket.handler.impl.LoginHandler;
import com.lxy.socket.handler.impl.SearchFriendHandler;
import com.lxy.socket.handler.impl.friend.AddFriendResponseHandler;
import com.lxy.socket.handler.impl.friend.FriendRequestHandler;
import com.lxy.socket.handler.impl.msg.MsgRequestHandler;
import com.lxy.socket.handler.impl.talk.DelTalkRequestHandler;
import com.lxy.socket.handler.impl.talk.TalkNoticeRequestHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LoggingHandler;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class IMServerChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Resource
    private ApplicationContext applicationContext;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new ObjDecoder());
        socketChannel.pipeline().addLast(applicationContext.getBean(LoginHandler.class));
        socketChannel.pipeline().addLast(applicationContext.getBean(SearchFriendHandler.class));
        socketChannel.pipeline().addLast(applicationContext.getBean(FriendRequestHandler.class));
        socketChannel.pipeline().addLast(applicationContext.getBean(AddFriendResponseHandler.class));
        socketChannel.pipeline().addLast(applicationContext.getBean(TalkNoticeRequestHandler.class));
        socketChannel.pipeline().addLast(applicationContext.getBean(DelTalkRequestHandler.class));
        socketChannel.pipeline().addLast(applicationContext.getBean(MsgRequestHandler.class));
        socketChannel.pipeline().addLast(new ObjEncoder());
    }
}
