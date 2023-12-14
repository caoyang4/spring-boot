package com.caoyang.springboot3.exception;

/**
 * @author caoyang
 * @create 2023-12-14 13:33
 */
public class UnSupportException extends RuntimeException {


    public UnSupportException(String message) {
        super(message);
    }

    public UnSupportException(String message, Exception e) {
        super(message, e);
    }
}
