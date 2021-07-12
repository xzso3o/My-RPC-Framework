package com.xzs;


import com.xzs.annotation.ServiceScan;
import com.xzs.serializer.CommonSerializer;
import com.xzs.transport.netty.server.NettyServer;

@ServiceScan
public class NettyTestServer {

    public static void main(String[] args) {
        NettyServer server = new NettyServer("127.0.0.1", 9999, CommonSerializer.PROTOBUF_SERIALIZER);
        server.start();
    }

}