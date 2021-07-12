package com.xzs;

import com.xzs.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FuckServiceImpl implements FuckService{
    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String fuck(FuckObject fuckObject) {
        logger.info("接收到：{}", fuckObject.getMessage());
        return "这是调用的返回值，id=" + fuckObject.getId();
    }
}
