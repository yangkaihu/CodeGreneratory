package com.code.bean;

import com.code.utils.PropertiesUtils;

/**
 * @author yangkaihu@yeah.net
 * @date 2024/5/4 17:54
 * @Descrition: 解析配置文件key 值
 */


public class Constant {
    public static Boolean IGNORE_TABLE_PERFIX;
    public static String SUFFIX_BEAN_PARAM;
    public final static String[] SLQ_DATE_TIME_TYPES=new String[]{"datetime","timestamp"};
    public final static String[] SLQ_DATE_TYPES=new String[]{"date"};
    public final static String[] SLQ_DECIMAL_TYPE=new String[]{"decimal","double","float"};
    public final static String[] SLQ_STRING_TYPE=new String[]{"char","varchar","text","mediumtext","longtext"};
    public final static String[] SLQ_INTEGER_TYPE=new String[]{"int","tinyint"};
    public final static String[] SLQ_LONG_TYPE=new String[]{"bigint"};

    static {
        IGNORE_TABLE_PERFIX = Boolean.valueOf(PropertiesUtils.getString("ignore.table.prefix")) ;
        SUFFIX_BEAN_PARAM = PropertiesUtils.getString("suffix.bean.param");
    }
}
