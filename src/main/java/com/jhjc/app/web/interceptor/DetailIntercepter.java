package com.jhjc.app.web.interceptor;

import com.jhjc.app.service.Memcached;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: yuanhy
 * Time: 2017-7-10  16:08
 * Description:
 */
public class DetailIntercepter implements HandlerInterceptor {
    private static Logger LOGGER = LoggerFactory.getLogger(DetailIntercepter.class);

    private static Set<String> excludeOptionSet = new HashSet<String>();

    static {
        excludeOptionSet.add("/xxx/xxxx");
    }

    @Autowired
    private Memcached memcached;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type, logintoken");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}