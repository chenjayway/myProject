package com.ctb.controller;

import com.ctb.util.MD5Util;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by jayway on 2017/6/20.
 */
public class RequestInterceptor implements HandlerInterceptor {
    static final  String token="";
    /**
     * contronler 前调用
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse resoponse, Object o) throws Exception {
       String sign=request.getParameter("sign");
       Map<String,String[]> paramMap=request.getParameterMap();
       Map<String,String[]> sortMap=new TreeMap<>();
       sortMap.putAll(paramMap);
       StringBuilder sb=new StringBuilder();
       for(Map.Entry<String,String[]> param:sortMap.entrySet()){
           String key=param.getKey();
           String[] value=param.getValue();
           String valueStr= Arrays.toString(value).replace("[","").replace("]","");
           if(sign.equals(key)) continue;
           sb.append(key).append("=").append(valueStr).append("&");
       }
        sb.append(token);
        String md5Server= MD5Util.MD5(sb.toString());
        if(md5Server.equals(sign)){
            return true;
        }
        return false;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
