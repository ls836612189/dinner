package com.tj.sys.service;

import com.tj.sys.BaseException.AppException;
import com.tj.sys.Entity.SysAuthFunc;
import com.tj.sys.mapper.reader.FuncListReader;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 2016/4/22.
 */
@Service
public class FuncListService {
    private static final Logger LOGGER = Logger.getLogger(FuncListService.class);
    @Autowired
    private FuncListReader funcListReader;

    public List<SysAuthFunc> queryFuncList(){
        try {
            return funcListReader.queryFuncList();
        }catch(AppException e){
            LOGGER.error("====FuncListService===查询数据库异常!==="+e.getMessage());
        }catch(Exception e){
            LOGGER.error("===FuncListService===异常!"+e.getMessage());
        }
        return null;
    }
}
