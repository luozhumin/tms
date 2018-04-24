package com.jhjc.app.common.exception;

/**
 * Author: yuanhy
 * Time: 2017-6-3  10:36
 * Description:
 */
public class HttpInvokeException extends Exception{

    private static final long serialVersionUID = 2677911834223283759L;

    public HttpInvokeException() {
    }

    public HttpInvokeException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpInvokeException(String message) {
        super(message);
    }

    public HttpInvokeException(Throwable cause) {
        super(cause);
    }
}