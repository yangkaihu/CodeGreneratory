package com.code;

import com.code.bean.TableInfo;
import com.code.builder.BuildTable;

import java.util.List;

public class CodeGreneratorApplication {
    public static void main(String[] args) {
         List<TableInfo>tableInfoList= BuildTable.getTabels();
    }
}
