package com.code.builder;

import com.code.bean.Constant;
import com.code.bean.FieldInfo;
import com.code.bean.TableInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


/**
 * @author yangkaihu@yeah.net
 * @date 2024/5/12 8:25
 * @desciption: 代码生成类
 */
public class BuildCode {

    private static final Logger logger = LoggerFactory.getLogger(BuildCode.class);

    public static void excutecode(TableInfo tableInfo) {
        File file = new File(Constant.PATH_PO);
        if (!file.exists()) {
            file.mkdirs();
        }
        File poFile = new File(file, tableInfo.getBeanName() + ".java");
        OutputStream out = null;
        OutputStreamWriter outw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(poFile);
            outw = new OutputStreamWriter(out, "utf8");
            bw = new BufferedWriter(outw);
            bw.write("package " + Constant.PACKAGE_PO + ";");
            bw.newLine();

            bw.write("import java.io.Serializable;");
            bw.newLine();
            if (tableInfo.getHaveDate() || tableInfo.getHaveDateTime()) {
                bw.write("import java.util.Date; ");
            }
            if (tableInfo.getHaveBigDecimal()){
                bw.write("import java.math.BigDecimal;\n");
            }
            bw.newLine();
            bw.newLine();

            BuildComments.createClassComment(bw,tableInfo.getComment());
            bw.write("public class " + tableInfo.getBeanName() + "  implements  " + " Serializable {");
            for (FieldInfo fieldInfo : tableInfo.getFieldList()){
               bw.newLine();
               BuildComments.createFiledComment(bw,fieldInfo.getComment());
               bw.write("private "+ fieldInfo.getJavaType()+" "+ fieldInfo.getPropertyName()+";");
               bw.newLine();
            }



            bw.newLine();
            bw.newLine();
            bw.write("}");
            bw.flush();
            logger.info("生成类的实例:succeefull");
        } catch (Exception e) {
            logger.error("创建失败" + e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (outw !=null){
                try {
                    outw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        File file = new File(Constant.PATH_PO);

    }
}
