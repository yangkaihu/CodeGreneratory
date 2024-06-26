package com.code.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @description: 配置类读取
 * @author: yangkaihu@yeah.net
 */
public class PropertiesUtils {

    /**
     * @descripation:  create empty properties with no default values
     */
     private static  Properties props= new Properties();
     // 把所有属性放在一个静态map 里
    /**
     * @describe: create empty list for storage proper
     */
    private  static Map<String,String> PROPER_MAP = new ConcurrentHashMap() {};
    // 项目初始化的时候就去读取配置文件application.properties
    static {
        InputStream is =null;
        try {
             is = PropertiesUtils.class.getClassLoader().getResourceAsStream("application.properties");
             props.load(new InputStreamReader(is,"utf8"));
            Iterator<Object> iterator1 = props.keySet().iterator();
            while (iterator1.hasNext()) {
                String key =(String) iterator1.next();
                PROPER_MAP.put(key,props.getProperty(key));
            }
        }catch (Exception e){

        }finally {
            if(is != null)
            {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
  public static String getString(String key){
        return PROPER_MAP.get(key);
  }

    public static void main(String[] args) {
        System.out.println("读取key值："+getString("db.driver.name"));
    }
}
