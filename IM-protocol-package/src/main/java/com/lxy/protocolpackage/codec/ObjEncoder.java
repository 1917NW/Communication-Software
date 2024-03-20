package com.lxy.protocolpackage.codec;

import com.lxy.protocolpackage.protocol.Packet;
import com.lxy.protocolpackage.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/*
  | 数据序列化后的字节长度 | 指令 | 数据序列化后的字节 |
  | 4 bytes           | 1 byte| n bytes |
 */
public class ObjEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet in, ByteBuf out) {
        byte[] data = SerializationUtil.serialize(in);
        out.writeInt(data.length);
        out.writeByte(in.getCommand()); //添加指令
        out.writeBytes(data);
    }

}