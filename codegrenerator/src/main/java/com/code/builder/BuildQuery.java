package com.code.builder;

import com.code.bean.Constant;
import com.code.bean.FieldInfo;
import com.code.bean.TableInfo;
import com.code.utils.DateUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangkaihu@yeah.net
 * @date 2024/7/24 10:57
 */
public class BuildQuery {
    private static final Logger logger = LoggerFactory.getLogger(BuildCode.class);

    public static void excutecode(TableInfo tableInfo) {
        File file = new File(Constant.PATH_QUERY);
        if (!file.exists()) {
            file.mkdirs();
        }
        String className=tableInfo.getBeanName()+Constant.SUFFIX_BEAN_QUERY;
        File poFile = new File(file, className+ ".java");
        OutputStream out = null;
        OutputStreamWriter outw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(poFile);
            outw = new OutputStreamWriter(out, "utf8");
            bw = new BufferedWriter(outw);
            bw.write("package " + Constant.PACKACE_QUERY + ";");
            bw.newLine();
            bw.newLine();
            if (tableInfo.getHaveBigDecimal()){
                bw.write("import java.math.BigDecimal;\n");

            }
            bw.newLine();
            if (tableInfo.getHaveDate() || tableInfo.getHaveDateTime()) {
                bw.write("import java.util.Date; ");
                bw.newLine();
            }
            bw.write(Constant.BEAN_LOOMBOK_CLASS+";");
            bw.newLine();
            bw.newLine();
            bw.newLine();
            BuildComments.createClassComment(bw,tableInfo.getComment());
            bw.write("@Data");
            bw.newLine();
            bw.write("public class " + className+" { ");
            bw.newLine();
            for (FieldInfo fieldInfo : tableInfo.getFieldList()){
                BuildComments.createFiledComment(bw,fieldInfo.getComment());
                bw.write("\t"+"private "+ fieldInfo.getJavaType()+" "+ fieldInfo.getPropertyName()+";");
                bw.newLine();
                // String 类型的参数
                if (ArrayUtils.contains(Constant.SLQ_STRING_TYPE,fieldInfo.getSqlType())){
                    bw.write("\t"+"private "+ fieldInfo.getJavaType()+" "+ fieldInfo.getPropertyName()+Constant.SUFFIX_BEAN_QUERY_FUZZY+";");
                    bw.newLine();
                    bw.newLine();
                }
                if (ArrayUtils.contains(Constant.SLQ_DATE_TIME_TYPES,fieldInfo.getSqlType()) || ArrayUtils.contains(Constant.SLQ_DATE_TYPES,fieldInfo.getSqlType())) {
                    bw.write("\t"+"private  String "+ " "+ fieldInfo.getPropertyName()+Constant.SUFFIX_BEAN_QUERY_TIME_START+";");
                    bw.newLine();
                    bw.newLine();
                    bw.write("\t"+"private  String "+ " "+ fieldInfo.getPropertyName()+Constant.SUFFIX_BEAN_QUERY_TIME_END+";");
                    bw.newLine();
                    bw.newLine();
                }
            }
            bw.newLine();
            bw.newLine();
            bw.write("}");
            bw.flush();

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

}
