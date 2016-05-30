package com.tj.core.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 2016/4/19.
 */
public class WebUtils {
    public static Object getSessionValue(HttpServletRequest request , String key){
        if(request.getSession() == null){
            return null;
        }else{
            return request.getSession().getAttribute(key);
        }
    }

    public static void setSession(HttpServletRequest request,String name,Object value){
        request.getSession(true).setAttribute(name,value);
    }

    public static Object getSession(HttpServletRequest request,String name){
        return request.getSession(true).getAttribute(name);
    }
}
