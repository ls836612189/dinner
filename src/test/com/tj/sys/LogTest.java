package com.tj.sys;

import com.tj.core.utils.Encriptor;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2016/4/19.
 */
public class LogTest {
    private static final Logger LOGGER = Logger.getLogger(LogTest.class);
    public static void main(String[] args){
//        PropertyConfigurator.configure(LogTest.class.getResourceAsStream("/config/log4j.properties"));
//        LOGGER.debug("debug");
//        LOGGER.info("info");
//        LOGGER.error("error");
//        LOGGER.warn("ware");
        Map<Integer,String> map = new HashMap<Integer,String>();
        map.put(1,"dd");

    }

    @Test
    public void MD5Test(){
        String md5 = Encriptor.getMD5("123456");
        System.out.println(md5);
    }

}
