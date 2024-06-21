package com.code;

import com.code.bean.TableInfo;
import com.code.builder.BuildBase;
import com.code.builder.BuildCode;
import com.code.builder.BuildTable;

import java.util.List;

public class CodeGreneratorApplication {
    public static void main(String[] args) {
        BuildBase.execute();
        List<TableInfo> tableInfoList = BuildTable.getTabels();
        for (TableInfo tableInfo : tableInfoList) {
            BuildCode.excutecode(tableInfo);
        }
    }
}
