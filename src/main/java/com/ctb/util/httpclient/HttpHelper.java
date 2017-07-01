/**
 * Copyright (c) 2015 iKang Guobin Healthcare Group. All rights reserved.
 */
package com.ctb.util.httpclient;

import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * HTTP工具类，封装HttpClient来对外提供简化的HTTP请求
 */
public class HttpHelper {
    protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(HttpHelper.class);
    private static Integer connectionTimeOut = 6000;
    
    private static Integer soTimeOut = 0;
 
    public void setConnectionTimeOut(Integer cTimeOut) {
        connectionTimeOut = cTimeOut;
    }
 
    public void setSoTimeOut(Integer sTimeOut) {
        soTimeOut = sTimeOut;
    }
 
    /**
     * 使用Get方式 根据URL地址，获取ResponseContent对象
     * 
     * @param url 完整的URL地址
     * @return ResponseContent 如果发生异常则返回null，否则返回ResponseContent对象
     */
    public static ResponseContent getUrlRespContent(String url) {
        HttpClientWrapper hw = new HttpClientWrapper(connectionTimeOut, soTimeOut);
        ResponseContent response = null;
        try {
            response = hw.getResponse(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
 
    /**
     * 使用Get方式 根据URL地址，获取ResponseContent对象
     * 
     * @param url
     *            完整的URL地址
     * @param urlEncoding
     *            编码，可以为null
     * @return ResponseContent 如果发生异常则返回null，否则返回ResponseContent对象
     */
    public static ResponseContent getUrlRespContent(String url, String urlEncoding) {
        HttpClientWrapper hw = new HttpClientWrapper(connectionTimeOut, soTimeOut);
        ResponseContent response = null;
        try {
            response = hw.getResponse(url, urlEncoding);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    
    /**
     * 将参数拼装在url中，进行post请求。
     * 
     * @param url
     * @return
     */
    public static ResponseContent postUrl(String url) {
        HttpClientWrapper hw = new HttpClientWrapper();
        ResponseContent ret = null;
        try {
            setParams(url, hw);
            ret = hw.postNV(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
 
    private static void setParams(String url, HttpClientWrapper hw) {
        String[] paramStr = url.split("[?]", 2);
        if (paramStr == null || paramStr.length != 2) {
            return;
        }
        String[] paramArray = paramStr[1].split("[&]");
        if (paramArray == null) {
            return;
        }
        for (String param : paramArray) {
            if (param == null || "".equals(param.trim())) {
                continue;
            }
            String[] keyValue = param.split("[=]", 2);
            if (keyValue == null || keyValue.length != 2) {
                continue;
            }
            hw.addNV(keyValue[0], keyValue[1]);
        }
    }
 
    /**
     * 使用post方式，发布对象转成的json给Rest服务。
     * 
     * @param url
     * @param jsonBody
     * @return
     */
    public static ResponseContent postJsonEntity(String url, String jsonBody) {
        return postEntity(url, jsonBody, "application/json");
    }
 
    /**
     * 使用post方式，发布对象转成的xml给Rest服务
     * 
     * @param url URL地址
     * @param xmlBody xml文本字符串
     * @return ResponseContent 如果发生异常则返回空，否则返回ResponseContent对象
     */
    public static ResponseContent postXmlEntity(String url, String xmlBody) {
        return postEntity(url, xmlBody, "application/xml");
    }
 
    private static ResponseContent postEntity(String url, String body,
            String contentType) {
        HttpClientWrapper hw = new HttpClientWrapper();
        RequestEntity entityBody;
        ResponseContent ret = null;
        try {
            entityBody = new StringRequestEntity(body, contentType, "utf-8");
            hw.setEntityBody(entityBody);
            ret = hw.postEntity(url);
        } catch (Exception e) {
            // 异常在本级结束，不再抛出
            logger.error("exception: {}", e.getStackTrace());
            logger.error("请求业务超时，请求url：{}", url);
        }
        return ret;
    }
 
    public static ResponseContent postEntity(String url, byte[] content) {
        HttpClientWrapper hw = new HttpClientWrapper();
        RequestEntity entityBody;
        ResponseContent ret = null;
        try {
            entityBody = new ByteArrayRequestEntity(content);
            hw.setEntityBody(entityBody);
            ret = hw.postEntity(url);
        } catch (Exception e) {
            // 异常在本级结束，不再抛出
            e.printStackTrace();
        }
        return ret;
    }
 
    public static ResponseContent postEntity(String url, InputStream content) {
        HttpClientWrapper hw = new HttpClientWrapper();
        RequestEntity entityBody;
        ResponseContent ret = null;
        try {
            entityBody = new InputStreamRequestEntity(content,
                    "multipart/form-data; boundary=ABCD");
            hw.setEntityBody(entityBody);
            ret = hw.postEntity(url);
        } catch (Exception e) {
            // 异常在本级结束，不再抛出
            e.printStackTrace();
        }
        return ret;
    }
}
