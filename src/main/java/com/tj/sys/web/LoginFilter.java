package com.tj.sys.web;


import com.tj.core.utils.*;
import com.tj.sys.Entity.SysAuthFunc;
import com.tj.sys.Entity.SysUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFilter implements Filter {
	private Pattern pattern = null;
	private String exclude=null;
		

	public void init(FilterConfig filterConfig) throws ServletException {
        pattern = Pattern.compile(CoreConstants.EXCLUDE_DIRECTORY_REGEX);
        exclude= Resources.getString(CoreConstants.EXCLUDE_URL_INIT);
	}


	public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
		SysUser sysUser = null;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String contextPath = httpServletRequest.getContextPath();
		String url = httpServletRequest.getServletPath();
		Matcher matcher = pattern.matcher(url);
        //request.setAttribute("fid",CoreConstants.getMenuParentId(url));
        // 如果路径中包含 '.'
        if (url.lastIndexOf(".") > 0) {
            url = url.substring(0, url.lastIndexOf("."));
        }
        if("/phone".equals(url.substring(0,6))){
            chain.doFilter(request, response);
        }
        if (matcher.find()) {
            //过滤掉静态资源文件
            chain.doFilter(request, response);
        } else {
            // 如果是需要过滤的url 不需要执行下面的代码
            if(exclude.indexOf(url)<0){
                sysUser = (SysUser) httpServletRequest.getSession().getAttribute(CoreConstants.SESSION_EMP);
                if (sysUser == null) {
                    // 如果没有登录 重定向到登录页面
                    httpServletRequest.getRequestDispatcher(CoreConstants.USER_HOME).forward(request, response);
                    return;
                } else {
                    request.setAttribute("fid",CoreConstants.getMenuParentId(url));
                    request.setAttribute("menu", WebUtils.getSession((HttpServletRequest)request,CoreConstants.SESSION_MENU));
//                    request.setAttribute("user",WebUtils.getSession((HttpServletRequest)request,CoreConstants.SESSION_EMP));
//                    Map<Long,SysAuthFunc> funcMap = CoreConstants.funcMap;
//                    request.setAttribute("funcs",funcMap);
                }
            }
            chain.doFilter(request, response);
        }
    }
    public void destroy() {
    }
}
