package com.caoyang.springboot3.repository;

import lombok.Getter;

/**
 * @author caoyang
 * @create 2023-12-14 16:51
 */
@Getter
public enum DataSourceType {

    /**
     * mysql
     */
    MYSQL("mysql"),

    /**
     * 缓存
     */
    REDIS("redis");

    private final String name;

    DataSourceType(String name) {
        this.name = name;
    }

}
