package com.code.bean;


import lombok.Data;



/**
 * @author yangkaihu@yeah.net
 * @date 2024/5/4 17:44
 * @descipation:    表字段结构信息；show  full fields from %s;
 */


@Data
public class FieldInfo  {


    /**
     * 字段名
     */
    private String FieldName;
    /**
     * bean 属性名称
     */
    private String propertyName;
    /**
     *对应SQL 类型
     */
    private String sqlType;
    /**
     * 对应Java字段类型
     */
    private String javaType;
    /**
     * 字段备注
     */
    private String comment;
    /**
     * 字段备注是否自增长
     */
    private Boolean isAadIncrement;


}
