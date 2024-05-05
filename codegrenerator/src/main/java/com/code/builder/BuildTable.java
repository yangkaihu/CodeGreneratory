package com.code.builder;

import com.code.bean.Constant;
import com.code.bean.FieldInfo;
import com.code.bean.TableInfo;
import com.code.utils.PropertiesUtils;
import com.code.utils.StringUtils;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import javafx.scene.control.Tab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuildTable {
    // 引入lg4j日志
    private static final Logger logger = LoggerFactory.getLogger(BuildTable.class);
    private static Connection connc = null;
    private static String  SQL_SHOW_TABLE_STATUS ="show table status";
    private static String  SQL_SHOW_FULL_FIELDS ="show full fields from %s";

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
    public static void getTabels(){
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
                //logger.info("tableName:{},comment:{}",tableName,comment);
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
                  logger.info("表名称 :{}, 备注: {}, JavaBean: {}",tableInfo.getTableName(),tableInfo.getComment(),tableInfo.getBeanParamName());

                 getreadFieldInfo(tableInfo);
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

    }
    private static List<FieldInfo> getreadFieldInfo(TableInfo tableInfo){
        PreparedStatement ps =null;
        ResultSet fieldResult =null;
        List<FieldInfo> fieldInfos = new ArrayList();

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
                fieldInfos.add(fieldInfo);
                fieldInfo.setFieldName(field);
                fieldInfo.setComment(comment);
                fieldInfo.setSqlType(type);
                fieldInfo.setIsAadIncrement("auto_increment".equalsIgnoreCase(extra)?true:false);
                fieldInfo.setPropertyName(propertyName);
                fieldInfo.setJavaType(StringUtils.javaType(type));
                // 打印验证数据正确性
                logger.info("field:{},type:{},extra:{},comment:{},propertyName:{}",field,type,extra,comment,propertyName);
                logger.info("=================================================");
                logger.info("javatype:{}",fieldInfo.getJavaType());

            }
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
        return fieldInfos;
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
