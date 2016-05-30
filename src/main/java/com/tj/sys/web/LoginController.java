package com.tj.sys.web;

import com.tj.core.utils.Auth;
import com.tj.core.utils.CoreConstants;
import com.tj.core.utils.Encriptor;
import com.tj.core.utils.WebUtils;
import com.tj.sys.Entity.MenuFunc;
import com.tj.sys.Entity.SysUser;
import com.tj.sys.response.BaseResponse;
import com.tj.sys.response.EnumResponse;
import com.tj.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by user on 2016/4/18.
 */
@Controller
public class LoginController {

    @Autowired
    public SysUserService sysUserService;

    @RequestMapping(value="/login",method= RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestParam(value="loginName",required=false,defaultValue="")String loginName,
                        @RequestParam(value="loginPwd",required=false,defaultValue="")String loginPwd,
                        @RequestParam(value="verifyCode",required=false,defaultValue="")String verifyCode,
                        HttpServletRequest request, ModelMap model){

        Object codeValue = WebUtils.getSessionValue(request, CoreConstants.VERIFYING_CODE_KEY);
        if(codeValue == null || !(verifyCode.equalsIgnoreCase(codeValue.toString()))){
            return new BaseResponse(EnumResponse.LOGIN_VERFYING);
        }
        SysUser sysUser = sysUserService.getSysUserByLoginName(loginName);
        if(sysUser != null && sysUser.getPassword().equalsIgnoreCase(Encriptor.getMD5(loginPwd))){
            WebUtils.setSession(request,CoreConstants.SESSION_EMP,sysUser);
            List<MenuFunc> sessionMenu= Auth.getSessionMenu(request, sysUser.getRoleIds());
            WebUtils.setSession(request, CoreConstants.SESSION_MENU, sessionMenu);
            return new BaseResponse(EnumResponse.LOGIN_SUCCESS);
        }else{
            return new BaseResponse(EnumResponse.LOGIN_NO_USER);
        }
    }
}
