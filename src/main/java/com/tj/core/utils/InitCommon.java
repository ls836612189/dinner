package com.tj.core.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2016/4/22.
 */
public class InitCommon extends HttpServlet {
    @Override
    public void init() throws ServletException {
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        Application.getInstance().runALL(ctx, this.getServletContext());
    }
}
