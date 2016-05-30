package com.tj.sys.mapper.reader;

import com.tj.sys.BaseException.AppException;
import com.tj.sys.Entity.SysUser;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 2016/4/19.
 */
@Repository
public interface SysUserReader {
    SysUser getSysUserByLoginName(String loginName);
}
