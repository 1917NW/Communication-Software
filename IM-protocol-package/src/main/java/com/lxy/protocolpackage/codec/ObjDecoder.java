package com.lxy.protocolpackage.codec;
import com.lxy.protocolpackage.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import com.lxy.protocolpackage.util.SerializationUtil;

public class ObjDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (in.readableBytes() < 4) {
            return;
        }
        in.markReaderIndex();
        // 读取数据长度
        int dataLength = in.readInt();

        // 判断剩余可读字节是否小于(数据长度 + 指令长度)
        if (in.readableBytes() < dataLength + 1) {
            in.resetReaderIndex();
            return;
        }
        byte command = in.readByte();  //读取指令

        byte[] data = new byte[dataLength]; //读取数据
        in.readBytes(data);
        out.add(SerializationUtil.deserialize(data, Packet.get(command)));
    }

}