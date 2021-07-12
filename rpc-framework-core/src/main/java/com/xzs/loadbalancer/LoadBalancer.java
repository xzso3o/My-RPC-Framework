package com.xzs.loadbalancer;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;

public interface LoadBalancer {

    Instance select(List<Instance> instances);

    //Zookeeper
    String selectServiceAddress(List<String> serviceAddresses, String rpcServiceName);

}