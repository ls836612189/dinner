package com.tj.sys.service;

import com.tj.sys.BaseException.AppException;
import com.tj.sys.Entity.SysUser;
import com.tj.sys.mapper.reader.SysUserReader;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2016/4/19.
 */
@Service
public class SysUserService {
    private static final Logger LOGGER = Logger.getLogger(SysUserService.class);
    @Autowired
    public SysUserReader sysUserReader;

    public SysUser getSysUserByLoginName(String loginName){
        try {
            return sysUserReader.getSysUserByLoginName(loginName);
        }catch (AppException e){
            LOGGER.error("===查询异常==="+e.getMessage());
        }
        return null;
    }
}
