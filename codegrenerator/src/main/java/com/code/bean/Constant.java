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
    static {
        IGNORE_TABLE_PERFIX = Boolean.valueOf(PropertiesUtils.getString("ignore.table.prefix")) ;
        SUFFIX_BEAN_PARAM = PropertiesUtils.getString("suffix.bean.param");
    }
}
