package com.tj.sys.mapper.reader;

import com.tj.sys.BaseException.AppException;
import com.tj.sys.Entity.SysAuthFunc;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 2016/4/22.
 */
@Repository
public interface FuncListReader {
    List<SysAuthFunc> queryFuncList();
}
