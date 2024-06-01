package com.code.builder;

import com.code.bean.Constant;
import com.code.utils.DateUtil;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Date;

/**
 * @author yangkaihu@yeah.net
 * @date 2024/5/26 5:28
 */
public class BuildComments {

    public static  void createClassComment (BufferedWriter bw,String ClassComment) throws IOException {
       bw.write("/**");
       bw.newLine();
       bw.write("*@Author: "+ Constant.AUTHOR_COMMENT);
       bw.newLine();
       bw.write("*@Description: " + ClassComment);
       bw.newLine();
       bw.write("*@Date: " + DateUtil.DateFormat(new Date(),DateUtil._YYYYMMDD));
       bw.newLine();
       bw.write("*/");
       bw.newLine();
    }
    public static  void  createFiledComment(BufferedWriter bw,String FiledComment) throws IOException{
       bw.write("\t /**");
       bw.newLine();
       bw.write("\t * "+(FiledComment== null ?  " " : FiledComment));
       bw.newLine();
       bw.write("\t */");
       bw.newLine();

    }
    public static  void cteateMethodComment(){

    }
}
