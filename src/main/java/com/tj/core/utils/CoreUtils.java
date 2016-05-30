package com.tj.core.utils;

/**
 * Created by user on 2016/4/18.
 */
public class CoreUtils {

    /**
     * 判断字符串是否为数字
     * @author guofu
     */
    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

}
