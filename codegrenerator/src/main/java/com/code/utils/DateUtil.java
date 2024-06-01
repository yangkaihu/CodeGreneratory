package com.code.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yangkaihu@yeah.net
 * @date 2024/5/26 5:46
 */
public class DateUtil {
    public static  final String YYYY_MM_DD_HH_MM_SS= "yyyy-MM-dd HH:mm:ss";
    public static  final String YYYY_MM_DD = "yyyy-MM-dd";
    public static  final String _YYYYMMDD = "yyyy/MM/dd";
    public static  String DateFormat(Date date,String patten){
        return new SimpleDateFormat(patten).format(date);
    }
    public static  String parse(String date,String patten){
        try {
            new SimpleDateFormat(patten).parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
