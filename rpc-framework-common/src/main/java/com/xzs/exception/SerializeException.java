package com.xzs.exception;

/**
 * 序列化异常
 *
 * @author xzs
 */
public class SerializeException extends RuntimeException {
    public SerializeException(String msg) {
        super(msg);
    }
}
