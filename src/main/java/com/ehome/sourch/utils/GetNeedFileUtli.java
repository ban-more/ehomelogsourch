package com.ehome.sourch.utils;

import ch.ethz.ssh2.Connection;
import com.ehome.sourch.pojo.Node;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 得到所需的日志文件名
 * Created by wzw on 2017/9/27.
 */
public class GetNeedFileUtli {

    private GetAllFileUtil getAllFileUtil;
    /**
     * 得到最近的日志文件名
     * @return
     */
    public String getNeeedFileName(Node node,String path,Connection conn){//取出最近的日志文件名
        getAllFileUtil = new GetAllFileUtil(node,path);

        String[] filenames = getAllFileUtil.getFile(conn);//获取指定目录下的所有文件
        String[] strs = new String[30];//取每个文件名中的日期
        String str=filenames[0];//取日期最大的文件名
        String st = null;
        for(int i=0; i<filenames.length; i++) {
            if (filenames[i] != null){
                String[] filename = filenames[i].split(String.valueOf('_'));//取每个文件名称切割后的字符串数组
                String[] strs2 = new String[3];
                strs2 = filename[3].split(".out");
                strs[i] = filename[2] + strs2[0];
                if(i==0){
                    st =  strs[i];
                }
                if (i > 0) {
                    if (Long.valueOf(st) < Long.valueOf(strs[i])) {
                        str = filenames[i];
                        st = strs[i];

                    }

                }
            }
        }
        System.out.println(str);
        return str;
    }

    /**
     * 得到指定日期内生成的日志文件的名称
     * @param date1
     * @param node
     * @param path
     * @return
     */
    public String getNeeedFileName(Date date1, Node node, String path, Connection conn) throws ParseException {//取出指定日期的文件名
        getAllFileUtil = new GetAllFileUtil(node,path);

        String[] filenames = getAllFileUtil.getFile(conn);//获取指定目录下的所有文件
        String[] strs = new String[30];//取每个文件名中的日期

        String str =null;//取符合日期的文件名
        String st = null;//盛放小于符合条件日期的最近日期的文件名
        String s = null;
        int j = 0;
        for(int i=0; i<filenames.length; i++) {
            if (filenames[i] != null) {
                String[] filename = filenames[i].split(String.valueOf('_'));//取每个文件名称切割后的字符串数组
                strs[i] = filename[2];
                DateFormat df = new SimpleDateFormat("yyyyMMdd");
                String date = df.format(date1);
                if (strs[i].equals(date)) {
                    st = filenames[i];
                    j = 1;
                } else if (Long.valueOf(strs[i]) < Long.valueOf(date)) {
                    if (i == 0||j==1) {
                        s = strs[i];
                        st = filenames[i];
                    }
                    System.out.println(s);
                    System.out.println(strs[i]);
                    if (Long.valueOf(s) < Long.valueOf(strs[i])) {
                        st = filenames[i];
                        s = strs[i];

                    }
                }
            }
        }
        if(str!=null) {
            System.out.println(str);
            return str;

        }else{
            System.out.println(st);
            return st;
        }

    }

    public String[] getNeeedFileName(String date1,String data2,Node node,String path,Connection conn) {//date1至date2之间的文件名
        getAllFileUtil = new GetAllFileUtil(node,path);

        String[] filenames = getAllFileUtil.getFile(conn);//获取指定目录下的所有文件
        String[] strs = new String[30];//取每个文件名中的日期
        String[] str = new String[30];//取符合日期的文件名
        int j =0;
        for (int i = 0; i < filenames.length; i++) {
            String[] filename = filenames[i].split(String.valueOf('_'));//取每个文件名称切割后的字符串数组
            strs[i] = filename[1] + filename[2].split(".out");
            if (Integer.valueOf(strs[i]) > Integer.valueOf(date1)&&Integer.valueOf(strs[i]) < Integer.valueOf(data2)) {
                str[j] = filenames[i];
                j++;
            }
        }
        return str;
    }
}
