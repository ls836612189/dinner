package com.tj.core.utils;

import org.springframework.util.Assert;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by user on 2016/4/19.
 */
public class Encriptor {
    public static String getMD5(String encrypt){
        Assert.notNull(encrypt,"非法参数异常!");
        String md5 = null;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(encrypt.getBytes());
            byte[] b = md.digest();
            int i = 0;
            StringBuffer buf = new StringBuffer();
            for (int offset = 0 ;offset < b.length ; offset++ ){
                i = b[offset];
                if(i < 0) i += 256;
                if(i < 16) buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            md5 = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }
}
