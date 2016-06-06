package com.tj.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 2016/5/11.
 */
public class DateUtils {
    public static String dateToYmdhmsString(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
    }
}
