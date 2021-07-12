package com.xzs;

import com.xzs.serializer.CommonSerializer;
import com.xzs.serializer.KryoSerializer;
import com.xzs.transport.RpcClient;
import com.xzs.transport.RpcClientProxy;
import com.xzs.transport.netty.client.NettyClient;

public class NettyTestClient {

    public static void main(String[] args) {
        RpcClient client = new NettyClient(CommonSerializer.KRYO_SERIALIZER);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService proxy1 = rpcClientProxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(111, "This is a HelloService message");

        FuckService proxy2 = rpcClientProxy.getProxy(FuckService.class);
        FuckObject object2 = new FuckObject(222, "This is a FuckService message");

        String res = proxy1.hello(object);
        System.out.println(res);

        String res2 = proxy2.fuck(object2);
        System.out.println(res2);
    }
}