package com.tj.core.utils;

import com.tj.sys.Entity.SysAuthFunc;
import org.apache.commons.lang.StringUtils;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2016/4/18.
 */
public class CoreConstants {
    public static final String USER_HOME = "/";
    public static final String FOODIMGPATH="E://TJfiles/";
    public static final String VERIFYING_CODE_KEY = "VERIFYING_CODE_KEY";
    public static final String SESSION_EMP = "emp_session";
    public static final String SESSION_MENU = "session_menu";
    public static final String EXCLUDE_ONLY_LOGIN = "^/json/";
    public static final String ROLE_SUPPER_ID=",1,";
    public static final String ROLE_BUSINESS_SUPPER_ID=",2,";

    public static List<SysAuthFunc> allMenu = new LinkedList<SysAuthFunc>();
    public static Map<Long,SysAuthFunc> funcMap=new LinkedHashMap<Long,SysAuthFunc>();

    public static final String CACHE_ROLE_URL_MAP = "roleUrlMap";
    public static final String CACHE_FUNC_MAP = "funcMap";


    public static final String EXCLUDE_DIRECTORY_REGEX="^/css/|^/images/|^/js/|^/images/|^/script/|^/img/|^/foodImg/";
    public static final String EXCLUDE_URL_INIT="exclude";

    public static long getMenuParentId(String url) {
        if (StringUtils.isEmpty(url))
            return 0;
//        Map<Long,SysAuthFunc> funcMap = (Map<Long,SysAuthFunc>)CacheUtils.get(CoreConstants.CACHE_FUNC_MAP);
//        if (funcMap == null) {
//            funcMap = CoreConstants.funcMap;
//        }
//        Map<Long,SysAuthFunc> funcMap = CoreConstants.funcMap;
        for (SysAuthFunc func : funcMap.values()) {
            String funcUrl = func.getFuncUrl();
            if (url.equalsIgnoreCase(funcUrl)) {
                if (func.getIsMenu() == 1)
                    return func.getFuncId();
                return getMenuId(func.getParentId());
            }
        }
        return 0;
    }
    //TODO:非目录的url  有待修改
    private static long getMenuId(Long fid) {
        if (funcMap.get(fid).getIsMenu() == 1)
            return fid;
        return getMenuId(funcMap.get(fid).getParentId());

    }

}
