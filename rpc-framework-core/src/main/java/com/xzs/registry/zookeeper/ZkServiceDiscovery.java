package com.xzs.registry.zookeeper;

import com.xzs.enumeration.RpcError;
import com.xzs.exception.RpcException;
import com.xzs.loadbalancer.LoadBalancer;
import com.xzs.loadbalancer.RandomLoadBalancer;
import com.xzs.registry.ServiceDiscovery;
import com.xzs.registry.zookeeper.util.CuratorUtils;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.List;

public class ZkServiceDiscovery implements ServiceDiscovery {

    private static final Logger logger = LoggerFactory.getLogger(ZkServiceDiscovery.class);

    private final LoadBalancer loadBalancer;

    public ZkServiceDiscovery(LoadBalancer loadBalancer) {
        if(loadBalancer == null) this.loadBalancer = new RandomLoadBalancer();
        else this.loadBalancer = loadBalancer;
    }

    //根据服务名到zookeeper寻找对应的服务地址
    @Override
    public InetSocketAddress lookupService(String serviceName) {
        System.out.println(serviceName + "11111111111");
        CuratorFramework zkClient = CuratorUtils.getZkClient();
        System.out.println(serviceName + "22222222222");
        List<String> serviceUrlList = CuratorUtils.getChildrenNodes(zkClient, serviceName);
        if (serviceUrlList == null || serviceUrlList.size() == 0) {
            throw new RpcException(RpcError.SERVICE_NOT_FOUND);
        }
        // 负载均衡
        String targetServiceUrl = loadBalancer.selectServiceAddress(serviceUrlList, serviceName);
        logger.info("成功寻找到服务地址 :[{}]", targetServiceUrl); //eg：192.168.199.171:6666
        String[] socketAddressArray = targetServiceUrl.split(":");
        // socketAddressArray[0]： 192.168.199.171
        String host = socketAddressArray[0];
        // socketAddressArray[1]： 6666
        int port = Integer.parseInt(socketAddressArray[1]);
        // 返回服务地址(ip + 端口号)
        return new InetSocketAddress(host, port);
    }
}
