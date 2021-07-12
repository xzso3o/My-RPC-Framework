package com.xzs;

import com.xzs.HelloObject;
import com.xzs.HelloService;
import com.xzs.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class HelloServiceImpl implements HelloService{
    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hello(HelloObject helloObject) {
        logger.info("接收到：{}", helloObject.getMessage());
        return "这是调用的返回值，id=" + helloObject.getId();
    }
}
