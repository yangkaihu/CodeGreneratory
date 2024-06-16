package com.code.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangkaihu@yeah.net
 * @date 2024/6/16 16:51
 */
public class DataUtil {
    private static final Object lockObj = new Object();
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

    private static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> t1 = sdfMap.get(pattern);
        if (t1 == null) {
            synchronized (lockObj) {
                t1 = sdfMap.get(pattern);
                if (t1 == null) {
                    t1 = new ThreadLocal<SimpleDateFormat>() {
                        @Override
                        protected SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern,t1);
                }
            }
        }
        return t1.get();
    }

    public static String format (Date date, String pattern){
        return getSdf(pattern).format(date);
    }




}
