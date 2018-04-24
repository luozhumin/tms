package com.jhjc.app.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.jhjc.app.common.exception.HttpInvokeException;
import com.jhjc.app.web.dto.ResponseVo;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Time: 2017-6-3  10:29
 * Description:
 */
public class HttpUtil {
    private static Logger log = LoggerFactory.getLogger(HttpUtil.class);
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";
    private static ExecutorService asyncInvokeExecutor = Executors.newSingleThreadExecutor();

    public HttpUtil() {
    }

    public static String doPost(String invokeUrl, String jsonParam) {
        try {
            return invoke(invokeUrl, "POST", jsonParam, true);
        } catch (HttpInvokeException var3) {
            log.error(var3.getMessage(), var3);
            return null;
        }
    }

    public static String doPost(String invokeUrl, String jsonParam, boolean logFlag) {
        try {
            return invoke(invokeUrl, "POST", jsonParam, logFlag);
        } catch (HttpInvokeException var4) {
            log.error(var4.getMessage(), var4);
            return null;
        }
    }

    public static String doGet(String invokeUrl) {
        try {
            return invoke(invokeUrl, "GET", (String)null, true);
        } catch (HttpInvokeException var2) {
            log.error(var2.getMessage(), var2);
            return null;
        }
    }

    public static String doGet(String invokeUrl, boolean logFlag) {
        try {
            return invoke(invokeUrl, "GET", (String)null, logFlag);
        } catch (HttpInvokeException var3) {
            log.error(var3.getMessage(), var3);
            return null;
        }
    }

    public static String invoke(String invokeUrl, String requestMethod, String clientData, boolean logFlag) throws HttpInvokeException {
        if(logFlag) {
            log.info("http invoke {} begin,param is {}", invokeUrl, getJsonString4Log(clientData));
        }

        HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
        client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
        client.getParams().setSoTimeout('\uea60');
        HttpMethod method = null;
        BufferedReader br = null;
        String result = "";

        try {
            method = getHttpMethod(invokeUrl, requestMethod, clientData);
            int httpCode = client.executeMethod(method);
            if(httpCode != 200) {
                throw new HttpInvokeException("http请求异常，异常状态码为" + httpCode);
            }

            br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),"utf-8"));
            StringBuffer temp = new StringBuffer();
            String str = "";

            while(true) {
                if((str = br.readLine()) == null) {
                    result = temp.toString();
                    break;
                }

                temp.append(str);
            }
        } catch (Exception var18) {
            log.error(var18.getMessage(), var18);
            throw new HttpInvokeException(var18);
        } finally {
            try {
                if(br != null) {
                    br.close();
                }
            } catch (Exception var17) {
                log.warn("关闭http请求流失败", var17);
            }

            if(method != null) {
                method.releaseConnection();
            }

        }

        if(logFlag) {
            log.info("http invoke success, result is {}", result);
        }

        return result;
    }

    public static void asyncInvoke(final String invokeUrl, final String requestMethod, final String clientData) {
        asyncInvokeExecutor.execute(new Runnable() {
            public void run() {
                try {
                    HttpUtil.log.info("async http invoke begin");
                    HttpUtil.invoke(invokeUrl, requestMethod, clientData, true);
                    HttpUtil.log.info("async http invoke success");
                } catch (HttpInvokeException var2) {
                    HttpUtil.log.error("异步请求处理失败", var2);
                }

            }
        });
    }

    private static HttpMethod getHttpMethod(String invokeUrl, String requestMethod, String clientData) throws UnsupportedEncodingException {
        if("GET".equals(requestMethod)) {
            GetMethod method = new GetMethod(invokeUrl);
            return method;
        } else if("PUT".equals(requestMethod)) {
            PutMethod method = new PutMethod(invokeUrl);
            if(StringUtils.isNotBlank(clientData)) {
                method.setRequestEntity(new StringRequestEntity(clientData, "application/json", "utf-8"));
            }

            return method;
        } else if("DELETE".equals(requestMethod)) {
            DeleteMethod method = new DeleteMethod(invokeUrl);
            method.setQueryString(StringUtils.trimToNull(clientData));
            return method;
        } else {
            PostMethod method = new PostMethod(invokeUrl);
            if(StringUtils.isNotBlank(clientData)) {
                method.setRequestEntity(new StringRequestEntity(clientData, "application/json", "utf-8"));
            }

            return method;
        }
    }

    public static void write(HttpServletResponse response, ResponseVo responseVo) {
        write(response, JSONObject.toJSONString(responseVo));
    }

    public static void writeImage(HttpServletResponse response, byte[] image) {
        try {
            OutputStream out = response.getOutputStream();
            out.write(image);
            out.flush();
        } catch (IOException var3) {
            log.error("http write error:", var3);
        }

    }

    public static void write(HttpServletResponse response, String responseStr) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            response.getWriter().write(responseStr);
        } catch (IOException var3) {
            log.error("http write error:", var3);
        }

    }

    public static String getRequestData(HttpServletRequest request) throws Exception {
        String param = null;
        if(!"GET".equalsIgnoreCase(request.getMethod()) && !"DELETE".equalsIgnoreCase(request.getMethod())) {
            StringBuffer data = new StringBuffer();
            BufferedReader reader = null;

            try {
                reader = request.getReader();
                String line = null;

                while(null != (line = reader.readLine())) {
                    data.append(line);
                }
            } catch (IOException var13) {
                log.error("获取请求参数失败", var13);
            } finally {
                try {
                    reader.close();
                } catch (Exception var12) {
                    ;
                }

            }

            param = data.toString();
        } else {
            param = request.getQueryString();
        }

        log.info("{}请求入参为：{}", request.getRequestURI(), getJsonString4Log(param));
        return param;
    }

    public static JSONObject getRequestDataJson(HttpServletRequest request) throws Exception {
        try {
            return JSONObject.parseObject(getRequestData(request));
        } catch (IOException var2) {
            log.error("入参转json失败", var2);
            return null;
        }
    }

    public static <T> T getRequestDataJson(HttpServletRequest request, Class<T> clazz) throws Exception {
        try {
            return JSONObject.parseObject(getRequestData(request), clazz);
        } catch (IOException var3) {
            log.error("入参转对象失败", var3);
            return null;
        }
    }

    private static String getJsonString4Log(String str) {
        if(StringUtils.isBlank(str)) {
            return "";
        } else {
            try {
                JSONObject oriObj = JSONObject.parseObject(str);
                JSONObject descObj = new JSONObject();

                String key;
                String value;
                for(Iterator var3 = oriObj.keySet().iterator(); var3.hasNext(); descObj.put(key, value)) {
                    key = (String)var3.next();
                    value = oriObj.getString(key);
                    if(StringUtils.isNotBlank(value) && value.length() > 500) {
                        value = StringUtils.substring(value, 0, 500) + "(已截取)";
                    }
                }

                return descObj.toJSONString();
            } catch (Exception var6) {
                return str;
            }
        }
    }

    public static String sendPostJSON(String url, String param) {
        log.info("sendPostJSON url:{},params:{}", url, getJsonString4Log(param));
        StringBuffer sb = new StringBuffer();
        OutputStreamWriter out = null;
        BufferedReader reader = null;

        try {
            URL postUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)postUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout('\uea60');
            connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            connection.setRequestProperty("content-type", "application/json");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            out.write(param);
            out.flush();
            out.close();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

            String line;
            while((line = reader.readLine()) != null) {
                sb.append(line);
            }

            reader.close();
            connection.disconnect();
        } catch (MalformedURLException var23) {
            var23.printStackTrace();
        } catch (IOException var24) {
            var24.printStackTrace();
        } finally {
            if(out != null) {
                try {
                    out.close();
                } catch (IOException var22) {
                    var22.printStackTrace();
                }
            }

            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException var21) {
                    var21.printStackTrace();
                }
            }

        }

        log.info("sendPostJSON result={}", sb.toString());
        return sb.toString();
    }

}