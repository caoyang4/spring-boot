package com.caoyang.springboot3.aop;

import com.caoyang.springboot3.annotation.DataSourceLog;
import com.caoyang.springboot3.repository.DataSourceType;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author caoyang
 * @create 2023-12-14 16:55
 */
@Aspect
@Component
@Slf4j
public class DataSourceLogAop {

    @Pointcut("@annotation(com.caoyang.springboot3.annotation.DataSourceLog)")
    public void pointcut(){}

    @Around("pointcut() && @annotation(annotation)")
    public Object around(ProceedingJoinPoint joinPoint, DataSourceLog annotation) throws Throwable {
        Object obj = joinPoint.proceed();
        if (Objects.nonNull(obj)) {
            String name = annotation.name();
            if (StringUtils.isEmpty(name)) {
                name =  joinPoint.getSignature().getName();
            }
            DataSourceType type = annotation.type();
            log.info("get Data from {} in {}", name, type.getName());
        }
        return obj;
    }

}
