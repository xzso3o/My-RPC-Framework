package com.xzs.registry.zookeeper;


import com.xzs.registry.ServiceRegistry;
import com.xzs.registry.zookeeper.util.CuratorUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;

import java.net.InetSocketAddress;

/**
 *  基于zookeeper的服务注册
 */
@Slf4j
public class ZkServiceRegistry implements ServiceRegistry {

    @Override
    public void register(String rpcServiceName, InetSocketAddress inetSocketAddress) {
        //eg: /my-rpc/com.xzs.HelloService/127.0.0.1:9999
        String servicePath = CuratorUtils.ZK_REGISTER_ROOT_PATH + "/" + rpcServiceName + inetSocketAddress.toString();
        CuratorFramework zkClient = CuratorUtils.getZkClient();
        // 创建持久节点。与临时节点不同，当客户端断开连接时不会删除持久节点
        CuratorUtils.createPersistentNode(zkClient, servicePath);
    }
}
