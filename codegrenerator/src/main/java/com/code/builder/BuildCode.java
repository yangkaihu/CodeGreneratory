package com.code.builder;

import com.code.bean.Constant;
import com.code.bean.FieldInfo;
import com.code.bean.TableInfo;

import java.io.File;
import java.io.IOException;


/**
 * @author yangkaihu@yeah.net
 * @date 2024/5/12 8:25
 * @desciption: 代码生成类
 */
public class BuildCode {
    public static void excutecode(TableInfo tableInfo){
        File file = new File(Constant.PATH_PO);
        if (!file.exists()){
            file.mkdirs();
        }
        File file1=new File(file,tableInfo.getBeanName()+".java");
        try {
            file1.createNewFile();
            System.out.println(file1);
            System.out.println("succeed！");
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        File file = new File(Constant.PATH_PO);
        System.out.println(file);

    }
}
