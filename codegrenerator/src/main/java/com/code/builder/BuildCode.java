package com.code.builder;

import com.code.bean.Constant;
import com.code.bean.TableInfo;

import java.io.File;


/**
 * @author yangkaihu@yeah.net
 * @date 2024/5/12 8:25
 * @desciption: 代码生成类
 */
public class BuildCode {
    public static void excutecode(TableInfo tableInfo){
        File file = new File(Constant.PATH_PO);
        if (!file.exists()){
            file.mkdir();
        }
    }

    public static void main(String[] args) {
        File file = new File(Constant.PATH_PO);

    }
}
