package com.code.builder;

import com.code.bean.Constant;
import com.code.bean.FieldInfo;
import com.code.bean.TableInfo;
import com.code.utils.JsonUtils;
import com.code.utils.PropertiesUtils;
import com.code.utils.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

public class BuildTable {
    // 引入lg4j日志
    private static final Logger logger = LoggerFactory.getLogger(BuildTable.class);
    private static Connection connc = null;
    private static String  SQL_SHOW_TABLE_STATUS ="show table status";
    private static String  SQL_SHOW_FULL_FIELDS ="show full fields from %s";
    private static String  SQL_SHOW_TABLE_INDEX = "show index from %s";

    static {
        String dirverName = PropertiesUtils.getString("db.driver.name");
        String url= PropertiesUtils.getString("db.url");
        String username= PropertiesUtils.getString("db.username");
        String password= PropertiesUtils.getString("db.password");

        try {
            Class.forName(dirverName);
            connc= DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            logger.error("mysql connection error",e);
        }

    }

    /**
     * 获取表结构信息
     */
    public static List<TableInfo> getTabels(){
        PreparedStatement ps =null;
        ResultSet tableResult =null;
        List<TableInfo> tableInfoList = new ArrayList();

        try {
            ps = connc.prepareStatement(SQL_SHOW_TABLE_STATUS);
            tableResult=ps.executeQuery();
            while (tableResult.next())
            {
                String tableName = tableResult.getString("name");
                String comment = tableResult.getString("comment");
                TableInfo tableInfo = new TableInfo();
                String beanName = tableName;
                if (Constant.IGNORE_TABLE_PERFIX)
                {
                    beanName = tableName.substring(beanName.indexOf("_")+1);
                }
                 beanName =processFiled(beanName,true);
                tableInfo.setTableName(tableName);
                tableInfo.setBeanName(beanName);
                tableInfo.setComment(comment);
                tableInfo.setBeanParamName(beanName+Constant.SUFFIX_BEAN_PARAM);
                getreadFieldInfo(tableInfo);
                getkeyindexinfo(tableInfo);
                tableInfoList.add(tableInfo);
            }

        } catch (Exception e) {
            logger.error("读取表失败",e);
        }finally {
            if (tableResult != null) {

                try {
                    tableResult.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }if (ps !=null)
            {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }if (connc !=null)
            {
                try {
                    connc.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return tableInfoList;

    }
    private static List<FieldInfo> getkeyindexinfo(TableInfo tableInfo){
        PreparedStatement ps =null;
        ResultSet fieldResult =null;
        List<FieldInfo> fieldInfos = new ArrayList();

        try {
            Map<String,FieldInfo> tempMap= new HashMap();
            for (FieldInfo fieldInfo : tableInfo.getFieldList())
            {
                tempMap.put(fieldInfo.getFieldName(),fieldInfo);
            }
            ps = connc.prepareStatement(String.format(SQL_SHOW_TABLE_INDEX,tableInfo.getTableName()));
            fieldResult=ps.executeQuery();
            while (fieldResult.next())
            {
                String Key_name = fieldResult.getString("Key_name");
                String Column_name = fieldResult.getString("Column_name");
                Integer Non_unique = fieldResult.getInt("Non_unique");
                if (Non_unique == 1){
                    continue;
                }
                List<FieldInfo> keyFieldList = tableInfo.getKeyIndexMap().get(Key_name);
                if (null == keyFieldList) {
                    keyFieldList=new ArrayList();
                    tableInfo.getKeyIndexMap().put(Key_name,keyFieldList);
                }
                keyFieldList.add(tempMap.get(Column_name));
            }
        } catch (Exception e) {
            logger.error("读取索引失败",e);
        }finally {
            if (fieldResult != null) {
                try {
                    fieldResult.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }if (ps !=null)
            {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return fieldInfos;
    }

    private static void getreadFieldInfo(TableInfo tableInfo){
        PreparedStatement ps =null;
        ResultSet fieldResult =null;
        List<FieldInfo> fieldInfoList = new ArrayList();

        try {
            ps = connc.prepareStatement(String.format(SQL_SHOW_FULL_FIELDS,tableInfo.getTableName()));
            fieldResult=ps.executeQuery();
            while (fieldResult.next())
            {
                String field = fieldResult.getString("field");
                String type = fieldResult.getString("type");
                String extra = fieldResult.getString("extra");
                String comment = fieldResult.getString("comment");
                if (type.indexOf("(")>0){
                    type=type.substring(0,type.indexOf("("));
                }
                String propertyName=processFiled(field,false);
                FieldInfo fieldInfo = new FieldInfo();
                fieldInfoList.add(fieldInfo);
                fieldInfo.setFieldName(field);
                fieldInfo.setComment(comment);
                fieldInfo.setSqlType(type);
                fieldInfo.setIsAadIncrement("auto_increment".equalsIgnoreCase(extra)?true:false);
                fieldInfo.setPropertyName(propertyName);
                fieldInfo.setJavaType(StringUtils.javaType(type));



                if (ArrayUtils.contains(Constant.SLQ_DATE_TIME_TYPES,type)){
                    tableInfo.setHaveDateTime(true);
                }else {
                    tableInfo.setHaveDateTime(false);
                }
                if (ArrayUtils.contains(Constant.SLQ_DATE_TYPES,type)){
                    tableInfo.setHaveDate(true);
                }else {
                    tableInfo.setHaveDate(false);
                }
                if (ArrayUtils.contains(Constant.SLQ_DECIMAL_TYPE,type)){
                    tableInfo.setHaveBigDecimal(true);
                }else {
                    tableInfo.setHaveBigDecimal(false);
                }
            }
            tableInfo.setFieldList(fieldInfoList);
        } catch (Exception e) {
            logger.error("读取表失败",e);
        }finally {
            if (fieldResult != null) {
                try {
                    fieldResult.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }if (ps !=null)
            {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static String processFiled(String filed,Boolean upercaseFirstLetter){

        StringBuffer sb = new StringBuffer();
        String[] fields = filed.split("_");
        sb.append(upercaseFirstLetter? StringUtils.toUperCaseFirstLetter(fields[0]) :fields[0] );
          for (int i=1,len = fields.length;i<len;  i++){
              sb.append(StringUtils.toUperCaseFirstLetter(fields[i]));
        }
          return sb.toString();
    }

}
