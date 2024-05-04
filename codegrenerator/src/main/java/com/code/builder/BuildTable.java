package com.code.builder;

import com.code.utils.PropertiesUtils;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class BuildTable {
    // 引入lg4j日志
    private static final Logger logger = LoggerFactory.getLogger(BuildTable.class);
    private static Connection connc = null;
    private static String  SQL_SHOW_TABLE_STATUS ="show table status";
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

    public static void getTabels(){
        PreparedStatement ps =null;
        ResultSet tableResult =null;

        try {
            ps = connc.prepareStatement(SQL_SHOW_TABLE_STATUS);
            tableResult=ps.executeQuery();
            while (tableResult.next())
            {
                String tableName = tableResult.getString("name");
                String comment = tableResult.getString("comment");
                logger.info("tableName:{},comment:{}",tableName,comment);
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

}
