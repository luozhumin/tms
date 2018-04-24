package com.jhjc.app.web.dto;

/**
 * Author: yuanhy
 * Time: 2017-9-8  11:22
 * Description:
 */
public class WeinXinJsConfig {

    private String timestamp;
    private String noncestr;
    private String signature;
    private String url;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}