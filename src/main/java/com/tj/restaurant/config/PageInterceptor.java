package com.tj.restaurant.config;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tj.restaurant.annotation.AutoPage;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 分页处理
 * Created by user on 2016/4/29.
 */
public class PageInterceptor implements HandlerInterceptor {
    /*
    请求处理之前调用
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            Method method = ((HandlerMethod) handler).getMethod();
            if(null != method.getAnnotation(AutoPage.class)){
                String pageNum = request.getParameter("pageNum");
                String pageSize = request.getParameter("pageSize");
                //请求路径中没有分页信息时，进行默认值处理
                if(StringUtils.isBlank(pageNum)){
                    pageNum = "1";
                }
                if(StringUtils.isBlank(pageSize)){
                    pageSize = "10";
                }

                // 将数据进行转换
                try{
                    PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
                }catch (Exception e){
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return false;
                }
            }
        }
        return true;
    }
    /*
    在preHandle返回true之后  进行调用  是在controller调用之后处理  但是在视图渲染之前 调用
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(handler instanceof HandlerMethod){
            Method method = ((HandlerMethod) handler).getMethod();
            if(null != method.getAnnotation(AutoPage.class)){
                // 对目标数据进行包装
                if(modelAndView.getModel().containsKey("pageData") && modelAndView.getModel().get("pageData") instanceof List){
                    try{
                        modelAndView.getModel().put("pageData",new PageInfo<List>((List)modelAndView.getModel().get("pageData")));
                    }catch (Exception e){
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    }
                }
            }
        }else{
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    /*
    整个请求完成之后进行调用
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
