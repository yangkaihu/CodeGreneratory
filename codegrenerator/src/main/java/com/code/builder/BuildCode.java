package com.code.builder;

import com.code.bean.Constant;
import com.code.bean.FieldInfo;
import com.code.bean.TableInfo;
import com.code.utils.DateUtil;
import org.apache.commons.lang3.ArrayUtils;
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
                bw.newLine();
                bw.write(Constant.BEAN_DATA_FORMAT_CLASS+";");
                bw.newLine();
                bw.write(Constant.BEAN_DATA_UNFORMAT_CLASS+";");
            }
            if (tableInfo.getHaveBigDecimal()){
                bw.write("import java.math.BigDecimal;\n");

            }
            bw.newLine();
              Boolean haveIgnoreBean = false;
              for (FieldInfo field : tableInfo.getFieldList()){
                  if (ArrayUtils.contains(Constant.IGNORE_BEAN_TOJSON_FILED.split(","),field.getPropertyName())){
                      haveIgnoreBean=true;
                      break;
                  }
              }
              if (haveIgnoreBean){
                  bw.write(Constant.IGNORE_BEAN_CLASS+";");
                  bw.newLine();
              }
              bw.write(Constant.BEAN_LOOMBOK_CLASS+";");
              bw.newLine();
            BuildComments.createClassComment(bw,tableInfo.getComment());
            bw.write("@Data");
            bw.newLine();
            bw.write("public class " + tableInfo.getBeanName() + "  implements  " + " Serializable {");
            for (FieldInfo fieldInfo : tableInfo.getFieldList()){
               bw.newLine();
               BuildComments.createFiledComment(bw,fieldInfo.getComment());
               if (ArrayUtils.contains(Constant.SLQ_DATE_TIME_TYPES,fieldInfo.getSqlType())){
                   bw.write("\t"+String.format(Constant.BEAN_DATA_FORMAT_EXPRESSION, DateUtil.YYYY_MM_DD_HH_MM_SS));
                   bw.newLine();
                   bw.write("\t"+String.format(Constant.BEAN_DATA_UNFORMAT_EXPRESSION,DateUtil.YYYY_MM_DD_HH_MM_SS));
               }
               if (ArrayUtils.contains(Constant.SLQ_DATE_TYPES,fieldInfo.getSqlType())){
                   bw.write("\t"+String.format(Constant.BEAN_DATA_FORMAT_EXPRESSION,DateUtil.YYYY_MM_DD));
                   bw.newLine();
                   bw.write("\t"+String.format(Constant.BEAN_DATA_UNFORMAT_EXPRESSION,DateUtil.YYYY_MM_DD));
               }
               if (ArrayUtils.contains(Constant.IGNORE_BEAN_TOJSON_FILED.split(","),fieldInfo.getPropertyName())){
                   bw.write("\t"+ String.format(Constant.IGNORE_BEAN_TOJSON_EXPRESSION));
               }
               bw.newLine();
               bw.write("\t"+"private "+ fieldInfo.getJavaType()+" "+ fieldInfo.getPropertyName()+";");
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
