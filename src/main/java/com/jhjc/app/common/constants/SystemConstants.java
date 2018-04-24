package com.jhjc.app.common.constants;

import org.apache.commons.lang3.StringUtils;
/**
 * Author: yuanhy
 * Time: 2017-4-10  15:59
 * Description:
 */
public class SystemConstants {

    // 测试地址
    private static String testUrl = StringUtils.EMPTY;




    public static String getTestUrl() {
        return testUrl;
    }

    public static void setTestUrl(String testUrl) {
        SystemConstants.testUrl = testUrl;
    }
}