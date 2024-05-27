package com.code.utils;

import com.code.bean.Constant;
import org.apache.commons.lang3.ArrayUtils;

import java.sql.Connection;

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
    public static String javaType(String type){
        if (ArrayUtils.contains(Constant.SLQ_INTEGER_TYPE,type)){
            return "Integer";
        }else if (ArrayUtils.contains(Constant.SLQ_LONG_TYPE,type)){
            return "Long";
        } else if (ArrayUtils.contains(Constant.SLQ_STRING_TYPE,type)) {
            return "String";
        } else if (ArrayUtils.contains(Constant.SLQ_DATE_TIME_TYPES,type)) {
            return "Date";
        } else if (ArrayUtils.contains(Constant.SLQ_DECIMAL_TYPE,type)) {
            return "BigDecimal";
        }else {
            throw new RuntimeException("无法识别的类型:"+ type);
        }


    }
}
