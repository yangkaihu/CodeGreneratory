package com.code.utils;

/**
 * @author yangkaihu@yeah.net
 * @date 2024/5/4 19:02
 */

public class StringUtils {
    public static String toUperCaseFirstLetter(String field){
        if (org.apache.commons.lang3.StringUtils.isEmpty(field)) {
            return field;
        }
        return field.substring(0,1).toUpperCase()+field.substring(1);
    }

    public static String toLowCaseFirstLetter(String field){
        if (org.apache.commons.lang3.StringUtils.isEmpty(field)) {
            return field;
        }
        return field.substring(0,1).toLowerCase()+field.substring(1);
    }

}
