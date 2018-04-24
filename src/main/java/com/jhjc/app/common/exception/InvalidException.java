package com.jhjc.app.common.exception;

/**
 * Author: yuanhy
 * Time: 2017-6-3  10:44
 * Description:无效参数异常
 */
public class InvalidException extends Exception {

    /**
     * serialVersionUID <br>
     */
    private static final long serialVersionUID = -3921228794467579968L;

    /**
     * serialVersionUID <br>
     */

    public InvalidException() {
        super();
    }

    public InvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidException(String message) {
        super(message);
    }

    public InvalidException(Throwable cause) {
        super(cause);
    }
}