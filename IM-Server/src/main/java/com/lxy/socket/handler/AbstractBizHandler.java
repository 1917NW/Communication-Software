package com.lxy.socket.handler;

import com.lxy.infrastructure.common.SocketChannelUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


public abstract class AbstractBizHandler<T> extends SimpleChannelInboundHandler<T> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, T msg) throws Exception {
        channelRead(ctx.channel(), msg);
    }

    public abstract void channelRead(Channel channel, T msg);

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        SocketChannelUtil.removeChannel(ctx.channel().id().toString());
        SocketChannelUtil.removeChannelFromAllGroup(ctx.channel());
        System.out.println("断开连接了");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause);
        SocketChannelUtil.removeChannel(ctx.channel().id().toString());
        SocketChannelUtil.removeChannelFromAllGroup(ctx.channel());
        System.out.println("关闭" + ctx.channel().id());
    }

}
