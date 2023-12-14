package com.caoyang.springboot3.constant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author caoyang
 * @create 2023-12-14 13:48
 */
public final class ThreadPoolConstant {


    public static final ExecutorService EXECUTOR = Executors.newVirtualThreadPerTaskExecutor();

}
