package com.xzs.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例工厂
 * @author xzs
 */
public class SingletonFactory {

    //对象都存在该Map中，保证只有一份
    private static Map<Class, Object> objectMap = new HashMap<>();

    private SingletonFactory() {}

    //首先看objectMap中是否有该实例对象，没有的话再创建
    public static <T> T getInstance(Class<T> clazz) {
        Object instance = objectMap.get(clazz);
        synchronized (clazz) {
            if(instance == null) {
                try {
                    //传进来一个class对象，通过clazz.newInstance()创建对象
                    instance = clazz.newInstance();
                    objectMap.put(clazz, instance);
                } catch (IllegalAccessException | InstantiationException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        }
        return clazz.cast(instance);
    }

}
