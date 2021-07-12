package com.xzs;

import com.xzs.serializer.CommonSerializer;
import com.xzs.serializer.KryoSerializer;
import com.xzs.transport.RpcClient;
import com.xzs.transport.RpcClientProxy;
import com.xzs.transport.netty.client.NettyClient;
import com.xzs.transport.socket.client.SocketClient;

public class SocketTestClient {
    public static void main(String[] args) {
        SocketClient client = new SocketClient(CommonSerializer.KRYO_SERIALIZER);
        RpcClientProxy proxy = new RpcClientProxy(client);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        for(int i = 0; i < 20; i ++) {
            String res = helloService.hello(object);
            System.out.println(res);
        }
    }
}
