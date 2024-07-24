package com.code.builder;

import com.code.bean.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangkaihu@yeah.net
 * @date 2024/6/14 14:27
 */
public class BuildBase {
    private static Logger logger = LoggerFactory.getLogger(BuildBase.class);

    public static void execute() {
        List<String> headInfoList = new ArrayList();
        // 生成枚举类
        headInfoList.add("package   " + Constant.PACKACE_ENUM_PATH);
        build(headInfoList,"DateTimePatternEnum",Constant.PACKACE_ENUM);
        headInfoList.clear();
        //  生成日期工具类
        headInfoList.add("package " + Constant.PACKAGE_UTILS_PATH);
        build(headInfoList, "DateUtil", Constant.PACKAGE_UTILS);
        logger.info("path: {} ", Constant.PACKAGE_UTILS);


    }

    private static void build(List<String> headerInfoList, String fileName, String outPutPath) {
        File folder = new File(outPutPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File javaFile = new File(outPutPath, fileName + ".java");
        logger.info("path: {}", outPutPath);
        logger.info("生成utils 文件：{}", javaFile);
        OutputStream out = null;
        OutputStreamWriter outw = null;
        BufferedWriter bw = null;

        InputStream in = null;
        InputStreamReader inr = null;
        BufferedReader bf = null;

        try {
            out = new FileOutputStream(javaFile);
            outw = new OutputStreamWriter(out, "utf-8");
            bw = new BufferedWriter(outw);

            String templatePath = BuildBase.class.getClassLoader().getResource("templeta/" + fileName + ".txt").getPath();

            in = new FileInputStream(templatePath);
            inr = new InputStreamReader(in, "utf-8");
            bf = new BufferedReader(inr);
            for (String head : headerInfoList) {
                bw.write(head + ";");
                bw.newLine();
            }
            String lineInfo = null;
            while ((lineInfo = bf.readLine()) != null) {
                bw.write(lineInfo);
                bw.newLine();
            }
            bw.flush();

        } catch (Exception e) {
            logger.error("生成基础类失败:{},失败：", fileName, e);
        } finally {
            if (bf != null) {
                try {
                    bf.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (inr != null) {
                try {
                    inr.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (outw != null) {
                try {
                    outw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

}
