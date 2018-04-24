package com.jhjc.app.web.dto;

/**
 * Author: yuanhy
 * Time: 2017-6-3  10:27
 * Description:
 */
public class ResponseVo<T> {
    private boolean success = true;
    private String errorMsg = "";
    private int errorCode = 0;
    private T data = null;

    public ResponseVo() {
    }

    public ResponseVo(boolean success, String errorMsg, int errorCode) {
        this.success = success;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public ResponseVo(boolean success, String errorMsg) {
        this.success = success;
        this.errorMsg = errorMsg;
    }

    public ResponseVo(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}