package com.jhjc.app.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author: yuanhy
 * Time: 2017-7-6  14:16
 * Description:
 */
public class ContextUtil {

    /**
     * localRequest
     */
    private static ThreadLocal<HttpServletRequest> localRequest = new ThreadLocal<HttpServletRequest>();

    /**
     * localResponse
     */
    private static ThreadLocal<HttpServletResponse> localResponse = new ThreadLocal<HttpServletResponse>();

    /**
     * 设置请求上下文，给aspect用的
     *
     * @param request <br>
     * @param response <br>
     */
    public static void setContext(HttpServletRequest request, HttpServletResponse response) {
        localRequest.set(request);
        localResponse.set(response);
    }

    /**
     * 本次请求Request
     */
    public static HttpServletRequest getRequest() {
        return localRequest.get();
    }

    /**
     * 本次请求Response
     */
    public static HttpServletResponse getResponse() {
        return localResponse.get();
    }
}