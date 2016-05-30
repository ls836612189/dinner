package com.tj.core.utils;

import com.tj.sys.Entity.MenuFunc;
import com.tj.sys.Entity.SysAuthFunc;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Auth {
	private static Pattern pattern = Pattern.compile(CoreConstants.EXCLUDE_ONLY_LOGIN);

    /**
     * 获取登录用户的session中存储的菜单目录结构
     * @param request
     * @param roleIds
     * @return
     */
	public static List<MenuFunc> getSessionMenu(HttpServletRequest request, String roleIds){
        List<MenuFunc> list = null;
        if (roleIds.indexOf(CoreConstants.ROLE_SUPPER_ID) >= 0 || roleIds.indexOf(CoreConstants.ROLE_BUSINESS_SUPPER_ID) >= 0) {
            list = getMenuListBySupperRole(CoreConstants.allMenu);
        }  else{
            Map<Long, List<SysAuthFunc>> map = (Map<Long,List<SysAuthFunc>>)CacheUtils.get(CoreConstants.CACHE_ROLE_URL_MAP);
            if (map == null ) {
            	map = (Map<Long,List<SysAuthFunc>>)request.getSession().getServletContext().getAttribute(CoreConstants.CACHE_ROLE_URL_MAP);
            }
            String [] tmp = roleIds.split(",");
            StringBuffer sb = new StringBuffer(",");
            for(String t:tmp){
                if(t == null || "".equals(t)){
                    continue;
                }
                List<SysAuthFunc> funcList = map.get(Long.parseLong(t));
                if(funcList == null){
                    continue;
                }
                for(SysAuthFunc func:funcList ){
                    sb.append(func.getFuncId());
                    sb.append(",");
                }
            }
            list = getMenuList(CoreConstants.allMenu, sb.toString());
        }
        return list;
	}

    /**
     * 超级用户展现的菜单结构
     * @param list
     * @return
     */
    private static List<MenuFunc> getMenuListBySupperRole(List<SysAuthFunc> list){

        List<MenuFunc> returnList  = new ArrayList<MenuFunc>();
        for(SysAuthFunc func : list){
            MenuFunc menuFunc = new MenuFunc();
            menuFunc.setIsChild(func.getIsChild());
            menuFunc.setFuncId(func.getFuncId());
            menuFunc.setFuncName(func.getFuncName());
            menuFunc.setFuncUrl(func.getFuncUrl());
            if(!func.getIsChild()){
                menuFunc.setChild(getMenuListBySupperRole(func.getChild()));
            }

            returnList.add(menuFunc);
        }
        return returnList;
    }
    private static List<MenuFunc> getMenuList(List<SysAuthFunc> list,String fundIds){

        List<MenuFunc> returnList  = new ArrayList<MenuFunc>();
        for(SysAuthFunc func : list){
            if(!fundIds.contains("," + func.getFuncId()+",")){
                continue;
            }
            MenuFunc menuFunc = new MenuFunc();
            menuFunc.setIsChild(func.getIsChild());
            menuFunc.setFuncId(func.getFuncId());
            menuFunc.setFuncName(func.getFuncName());
            menuFunc.setFuncUrl(func.getFuncUrl());
            if(!func.getIsChild()){
                menuFunc.setChild(getMenuList(func.getChild(),fundIds));
            }
            returnList.add(menuFunc);
        }
        return returnList;
    }


}
