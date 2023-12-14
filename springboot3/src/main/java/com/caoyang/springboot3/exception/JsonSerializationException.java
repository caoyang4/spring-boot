package com.caoyang.springboot3.exception;

/**
 * @author caoyang
 * @create 2023-12-14 13:33
 */
public class JsonSerializationException extends RuntimeException {


    public JsonSerializationException(String message) {
        super(message);
    }

    public JsonSerializationException(String message, Exception e) {
        super(message, e);
    }
}
