package com.xzs.provider;

/**
 * 保存和提供服务实例对象
 * @author xzs
 */
public interface ServiceProvider {


    /*<T> void addServiceProvider(T service);

    Object getServiceProvider(String serviceName);*/

    <T> void addServiceProvider(T service, String serviceName);

    Object getServiceProvider(String serviceName);

}
