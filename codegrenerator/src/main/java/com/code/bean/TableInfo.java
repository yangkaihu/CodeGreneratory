package com.code.bean;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import sun.reflect.FieldInfo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * @author yangkaihu@yeah.net
 * @date 2024/5/4 17:44
 */
@Data
public class TableInfo {
    /**
     * 表名称
     */
    private String tableName;

    /**
     * bean 名称
     */
    private String beanName;
    /**
     * 参数名称
     */
    private String beanParamName;
    /**
     * 表注解
     */
    private String comment;
    /**
     * 字段信息
     */
    private List<FieldInfo>fieldList;
    /**
     * 唯一索引集合
     */
    private Map<String,List<FieldInfo>> keyIndexMap = new LinkedHashMap();
    /**
     * 是否有data 类型
     */
    private Boolean haveDate;
    /**
     * 是否有时间类型
     */
    private Boolean haveDateTime;
    /**
     * 是否有bigdecimal 类型
     */
    private Boolean haveBigDecimal;
}
