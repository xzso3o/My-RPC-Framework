package com.xzs.codec;

import com.xzs.entity.RpcRequest;
import com.xzs.enumeration.PackageType;
import com.xzs.serializer.CommonSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class CommonEncoder extends MessageToByteEncoder {

    private static final int MAGIC_NUMBER = 0xCAFEBABE;

    private final CommonSerializer serializer;

    public CommonEncoder(CommonSerializer serializer) {
        this.serializer = serializer;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        out.writeInt(MAGIC_NUMBER);
        // 标明了这是一个调用请求还是调用响应
        if(msg instanceof RpcRequest) {
            out.writeInt(PackageType.REQUEST_PACK.getCode());
        } else {
            out.writeInt(PackageType.RESPONSE_PACK.getCode());
        }
        // 标明了实际数据使用的序列化器
        out.writeInt(serializer.getCode());
        // 将消息进行序列化
        byte[] bytes = serializer.serialize(msg);
        // 标明了实际数据的长度
        out.writeInt(bytes.length);
        // 标明了实际数据
        out.writeBytes(bytes);
    }
}
