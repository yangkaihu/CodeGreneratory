package com.code.bean;

import com.code.utils.PropertiesUtils;

/**
 * @author yangkaihu@yeah.net
 * @date 2024/5/4 17:54
 * @Descrition: 解析配置文件key 值
 */

public class Constant {
    public static String  AUTHOR_COMMENT;
    public static Boolean IGNORE_TABLE_PERFIX;
    public static String SUFFIX_BEAN_PARAM;
    public final static String[] SLQ_DATE_TIME_TYPES=new String[]{"datetime","timestamp"};
    public final static String[] SLQ_DATE_TYPES=new String[]{"date"};
    public final static String[] SLQ_DECIMAL_TYPE=new String[]{"decimal","double","float"};
    public final static String[] SLQ_STRING_TYPE=new String[]{"char","varchar","text","mediumtext","longtext"};
    public final static String[] SLQ_INTEGER_TYPE=new String[]{"int","tinyint"};
    public final static String[] SLQ_LONG_TYPE=new String[]{"bigint"};
    public static  String PATH_JAVA="java";
    public static String PATH_resource="resource";

    public static String PATH_BASE;

    public static String PATH_PO;
    public static String PACKACE_BASE;
    public static String PACKAGE_PO;

    static {
        AUTHOR_COMMENT=PropertiesUtils.getString("author.comment");
        IGNORE_TABLE_PERFIX = Boolean.valueOf(PropertiesUtils.getString("ignore.table.prefix")) ;
        SUFFIX_BEAN_PARAM = PropertiesUtils.getString("suffix.bean.param");
        PACKACE_BASE = PropertiesUtils.getString("pachkage.base");
        //po
        PACKAGE_PO = PACKACE_BASE +"."+PropertiesUtils.getString("package.po");

        PACKACE_BASE = PropertiesUtils.getString("pachkage.base");

        PATH_BASE=PropertiesUtils.getString("path.base");
        PACKAGE_PO = PACKACE_BASE + "." + PropertiesUtils.getString("package.po");
        PATH_BASE = PATH_BASE +PATH_JAVA;
        PATH_PO = PATH_BASE+"/"+PACKAGE_PO.replace(".","/");
    }

    public static void main(String[] args) {

        System.out.println(PACKAGE_PO);
        System.out.println(PATH_PO);

    }


}
