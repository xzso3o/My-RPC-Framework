package com.xzs;


import com.xzs.annotation.ServiceScan;
import com.xzs.registry.ServiceRegistry;
import com.xzs.serializer.CommonSerializer;
import com.xzs.serializer.KryoSerializer;
import com.xzs.transport.RpcServer;
import com.xzs.transport.socket.server.SocketServer;

@ServiceScan
public class SocketTestServer {
    public static void main(String[] args) {
        RpcServer server = new SocketServer("127.0.0.1", 9998, CommonSerializer.HESSIAN_SERIALIZER);
        server.start();
    }
}
