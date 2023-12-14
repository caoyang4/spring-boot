package com.caoyang.springboot3.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author caoyang
 * @create 2023-12-14 15:12
 */
@Slf4j
@WebFilter(urlPatterns = "/*", filterName = "urlFilter")
public class UrlFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        long start = System.currentTimeMillis();
        chain.doFilter(request, response);
        long end = System.currentTimeMillis();
        log.info("接口[{}]执行时间：{} 毫秒 ", httpServletRequest.getRequestURI(), (end - start));
    }

    @Override
    public void destroy() {
        log.info("UrlFilter destroy");
    }

}
