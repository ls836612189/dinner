package com.tj.core.utils;
import com.tj.sys.Entity.MenuFunc;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuTag extends TagSupport {

    private static final long serialVersionUID = 1L;
    private  List<MenuFunc> menuMap;
    private Integer fid;

    private static Map<Integer,String> ulClass = new HashMap<Integer,String>();
    private static Map<Integer,String> liClass = new HashMap<Integer,String>();
    static{
        ulClass.put(1,"nav_icon");
        liClass.put(1,"t1");
        ulClass.put(2,"chd_ul");
    }


    public StringBuffer getHtml(List<MenuFunc> func,String context,String url,int floor){

        StringBuffer sb = new StringBuffer();
        if(func.size()<=0){
            return sb;
        }
        sb.append("<ul class='");
        if(ulClass.get(floor) != null){
            sb.append(ulClass.get(floor));
        }
        sb.append("'>");
        for(MenuFunc menuFunc : func){
            sb.append(" <li class='");
            if(liClass.get(floor) != null){
                sb.append(liClass.get(floor));
            }
            if(floor == 2 && menuFunc.getIsChild()){
                sb.append( " sub_li ");
            }
            if(menuFunc.getFuncId() == fid){
                sb.append(" chd_on ");
            }
            sb.append("'>");
            sb.append("<a href='");
            if(!menuFunc.getFuncUrl().equals("#")) {
                sb.append(context);
            }
            sb.append(menuFunc.getFuncUrl());//添加链接
            sb.append("' title='");
            sb.append(menuFunc.getFuncName());
            sb.append("'>");
            sb.append(menuFunc.getFuncName());
            if( !menuFunc.getIsChild()){
                sb.append("<span class=\"arr li_icon\"></span>");
            }

            sb.append("</a>");

            if(!menuFunc.getIsChild()){
                sb.append(getHtml(menuFunc.getChild(),context,url,floor+1));
            }

            sb.append("</li>");
        }
        sb.append("</ul>");
        return sb;
    }


    @SuppressWarnings("static-access")
    public int doStartTag() {
        ServletRequest servletRequest = pageContext.getRequest();
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String context = request.getContextPath();
        String url  =  request.getServletPath();
        if (url.lastIndexOf(".") > 0) {
            url = url.substring(0, url.lastIndexOf("."));
        }
        try {
            StringBuffer sb = getHtml(menuMap,context,url,1);
            JspWriter out = pageContext.getOut();
            out.print(sb.toString());
            out.flush();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return super.SKIP_BODY;
    }

    public int doEndTag() {
        return EVAL_PAGE;
    }

    public List<MenuFunc> getMenuMap() {
        return menuMap;
    }

    public void setMenuMap(List<MenuFunc> menuMap) {
        this.menuMap = menuMap;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public void release() {
        super.release();
    }

}
