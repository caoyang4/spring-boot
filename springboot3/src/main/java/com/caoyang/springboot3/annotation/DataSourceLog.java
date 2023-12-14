package com.caoyang.springboot3.annotation;

import com.caoyang.springboot3.repository.DataSourceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author caoyang
 * @create 2023-12-14 16:48
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceLog {

    String name() default "";

    DataSourceType type();

}
