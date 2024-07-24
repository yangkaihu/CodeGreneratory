package com.code.bean;

import com.code.utils.PropertiesUtils;

import java.util.Properties;

/**
 * @author yangkaihu@yeah.net
 * @date 2024/5/4 17:54
 * @Descrition: 解析配置文件key 值
 */
public class Constant {

    public static String BEAN_LOMBOK_EXPRESSION;
    public static String BEAN_LOOMBOK_CLASS;
    // 需要忽略的的属性
    public static String IGNORE_BEAN_TOJSON_FILED;
    public static String IGNORE_BEAN_TOJSON_EXPRESSION;
    public static String IGNORE_BEAN_CLASS;
    // 日期序列化与反序列化
    public static String BEAN_DATA_FORMAT_EXPRESSION;
    public static String BEAN_DATA_FORMAT_CLASS;
    public static String BEAN_DATA_UNFORMAT_EXPRESSION;
    public static String BEAN_DATA_UNFORMAT_CLASS;
    public static String AUTHOR_COMMENT;
    public static Boolean IGNORE_TABLE_PERFIX;
    public static String SUFFIX_BEAN_QUERY;
    public final static String[] SLQ_DATE_TIME_TYPES = new String[]{"datetime", "timestamp"};
    public final static String[] SLQ_DATE_TYPES = new String[]{"date"};
    public final static String[] SLQ_DECIMAL_TYPE = new String[]{"decimal", "double", "float"};
    public final static String[] SLQ_STRING_TYPE = new String[]{"char", "varchar", "text", "mediumtext", "longtext"};
    public final static String[] SLQ_INTEGER_TYPE = new String[]{"int", "tinyint"};
    public final static String[] SLQ_LONG_TYPE = new String[]{"bigint"};
    public static String PATH_JAVA = "java";
    public static String PATH_resource = "resource";
    public static String PATH_BASE;
    public static String PATH_PO;
    public static String PACKACE_BASE;
    public static String PACKACE_QUERY;
    public static String PATH_QUERY;
    public static String PACKAGE_PO;
    public static String PACKACE_ENUM;
    public static String PACKACE_ENUM_PATH;
    public static String PACKAGE_UTILS;
    public static String PACKAGE_UTILS_PATH;

    public static String SUFFIX_BEAN_QUERY_FUZZY;
    public static String SUFFIX_BEAN_QUERY_TIME_START;
    public static String SUFFIX_BEAN_QUERY_TIME_END;

    static {
        // 导入lombok 插件
        BEAN_LOMBOK_EXPRESSION = PropertiesUtils.getString("bean.lombok.expression");
        BEAN_LOOMBOK_CLASS = PropertiesUtils.getString("bean.lombok.class");
        // 需要忽略的的属性
        IGNORE_BEAN_TOJSON_FILED = PropertiesUtils.getString("ignore.bean.tojson.filed");
        IGNORE_BEAN_TOJSON_EXPRESSION = PropertiesUtils.getString("ignore.bean.tojson.expression");
        IGNORE_BEAN_CLASS = PropertiesUtils.getString("ignore.bean.class");
        //  日期格式反序列
        BEAN_DATA_FORMAT_EXPRESSION = PropertiesUtils.getString("bean.data.format_expression");
        BEAN_DATA_FORMAT_CLASS = PropertiesUtils.getString("bean.data.format.class");
        BEAN_DATA_UNFORMAT_EXPRESSION = PropertiesUtils.getString("bean.data.unformat.expression");
        BEAN_DATA_UNFORMAT_CLASS = PropertiesUtils.getString("bean.data.unformat.class");

        AUTHOR_COMMENT = PropertiesUtils.getString("author.comment");
        IGNORE_TABLE_PERFIX = Boolean.valueOf(PropertiesUtils.getString("ignore.table.prefix"));
        SUFFIX_BEAN_QUERY = PropertiesUtils.getString("suffix.bean.query");

         SUFFIX_BEAN_QUERY_FUZZY =PropertiesUtils.getString("suffix.bean.query.fuzzy");
         SUFFIX_BEAN_QUERY_TIME_START=PropertiesUtils.getString("suffix.bean.query.time.start");
         SUFFIX_BEAN_QUERY_TIME_END=PropertiesUtils.getString("suffix.bean.query.time.end");

        PACKACE_BASE = PropertiesUtils.getString("pachkage.base");

        PACKAGE_PO = PACKACE_BASE + "." + PropertiesUtils.getString("package.po");

        PACKACE_BASE = PropertiesUtils.getString("pachkage.base");//com.codejava

        PACKAGE_UTILS = PropertiesUtils.getString("package.utils");
        PACKACE_ENUM = PropertiesUtils.getString("package.enum");// 枚举类
        PACKAGE_UTILS_PATH = PACKACE_BASE + "." + PACKAGE_UTILS;
        PACKACE_ENUM_PATH = PACKACE_BASE + "." + PACKACE_ENUM;
        PATH_BASE = PropertiesUtils.getString("path.base");
        PACKAGE_PO = PACKACE_BASE + "." + PropertiesUtils.getString("package.po");
        PATH_BASE = PATH_BASE + PATH_JAVA;
        PATH_PO = PATH_BASE + "/" + PACKAGE_PO.replace(".", "/");


        PACKACE_QUERY = PropertiesUtils.getString("package.query");
        PACKAGE_UTILS = PATH_BASE + "/" + PACKACE_BASE.replace(".", "/") + "/" + PACKAGE_UTILS.replace(".", "/");
        PACKACE_ENUM = PATH_BASE + "/" + PACKACE_BASE.replace(".", "/") + "/" + PACKACE_ENUM.replace(".", "/");
        PATH_QUERY = PATH_BASE + "/" + PACKACE_BASE.replace(".", "/") + "/" + PACKACE_QUERY.replace(".", "/");
        PACKACE_QUERY = PACKACE_BASE + "." + PACKACE_QUERY;
    }

    public static void main(String[] args) {
        System.out.println(PACKACE_BASE);
        System.out.println(PATH_BASE);
        System.out.println(PATH_QUERY);
        System.out.println(PACKACE_QUERY);

    }


}
