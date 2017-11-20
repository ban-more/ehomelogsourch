package com.ehome.sourch.utils;

import ch.ethz.ssh2.Connection;
import com.ehome.sourch.pojo.Node;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Arrays;

/**
 * 得到所需的日志文件名
 * Created by wzw on 2017/9/27.
 */
public class GetNeedFileUtil {

    private GetAllFileUtil getAllFileUtil;

//    public String[] getRightSrvFile(Node node, String[] filenames){
//        for(int i=0; i<filenames.length; i++){
//            String[] filename = filenames[i].split(String.valueOf('_'));
//            String[] strs2 = new String[3];
//            strs2 = filename[0].split("-");
//            if()
//        }
//    }

    /**
     * 得到最近的日志文件名
     * @return
     */
    public String[] getNeeedFileName(Node node,Connection conn){//取出最近的日志文件名
        getAllFileUtil = new GetAllFileUtil();
        String[] filenames = getAllFileUtil.getFile(node.getPath1(),conn);//获取指定目录下的所有文件
        String[] filenames2 = getAllFileUtil.getFile(node.getPath2(),conn);
        int f = filenames.length;
        int f2 = filenames2.length;
        filenames = Arrays.copyOf(filenames,filenames.length+filenames2.length);
        System.arraycopy(filenames2, 0, filenames, f, f2);
        String strs = null;//取每个文件名中的日期
        String str1 = null;
        String str2 = null;//取日期最大的文件名
        String st1 = null;
        String st2 = null;
        int j = 0;
        int k = 0;
        for(int i=0; i<filenames.length; i++) {
            if (filenames[i] != null){
                String[] filename = filenames[i].split(String.valueOf('_'));//取每个文件名称切割后的字符串数组
                String[] strs1 = filename[1].split("-");//取判断节点的信息
                String[] strs2 = filename[3].split(".out");
                strs = filename[2] + strs2[0];
                if("01".equals(strs1[2])) {
                    if (j == 0) {
                        st1 = strs;
                        j++;
                    }
                    if (j > 0) {
                        if (Long.valueOf(st1) < Long.valueOf(strs)) {
                            str1 = filenames[i];
                            st1 = strs;
                            j++;
                        }

                    }
                }
                if("02".equals(strs1[2])) {
                    if (k == 0) {
                        st2 = strs;
                        k++;
                    }
                    if (k > 0) {
                        if (Long.valueOf(st2) < Long.valueOf(strs)) {
                            str2 = filenames[i];
                            st2 = strs;
                            k++;
                        }

                    }
                }
            }
        }
        String[] filename = new String[2];
        if(str1 != null) {
            if(str2 != null) {
                filename[0] = str1;
                filename[1] = str2;
                System.out.println("节点1日志文件为："+str1+"节点2日志文件为："+str2);
                return filename;
            }else{
                filename[0] = str1;
                filename[1] = null;
                System.out.println("节点1日志文件为："+str1+"节点2没有发现日志文件！");
                return filename;
            }
        }else{
            if(str2 != null) {
                filename[0] = null;
                filename[1] = str2;
                System.out.println("节点1没有发现日志文件！"+"节点二日志文件为："+str2);
                return filename;
            }else{
                filename[0] = null;
                filename[1] = null;
                System.out.println("节点2没有发现日志文件！"+"节点2没有发现日志文件！");
                return filename;
            }
        }
    }

    /**
     * 得到指定日期内生成的日志文件的名称
     * @param date1
     * @param node
     * @return
     */
    public String[] getNeeedFileName(Date date1, Node node, Connection conn) throws ParseException {//取出指定日期的文件名
        getAllFileUtil = new GetAllFileUtil();

        String[] filenames = getAllFileUtil.getFile(node.getPath1(),conn);//获取指定目录下的所有文件
        String[] filenames2 = getAllFileUtil.getFile(node.getPath2(),conn);
        int f = filenames.length;
        int f2 = filenames2.length;
        filenames = Arrays.copyOf(filenames,filenames.length+filenames2.length);
        System.arraycopy(filenames2, 0, filenames, f, f2);
        String strs = null;//取每个文件名中的日期//取每个文件名中的日期
        DateFormat df = new SimpleDateFormat("yyyyMMdd");//将日期转换成字符串类型
        String date = df.format(date1);
        String[] st1 = null;//盛放符合条件日期的文件名
        String[] st2 = null;
        String str1 = null;//盛放小于符合条件日期的最近日期的文件名
        String str2 = null;
        String s = null;
        Boolean flag1 = true;
        Boolean flag2 = true;
        int j = 0;
        int k = 0;
        for(int i=0; i<filenames.length; i++) {
            if (filenames[i] != null) {
                String[] filename = filenames[i].split(String.valueOf('_'));//取每个文件名称切割后的字符串数组
                String[] strs1 = filename[1].split("-");//取判断节点的信息
                strs = filename[2];
                if("01".equals(strs1[2])) {
                    if (strs.equals(date)) {
                        st1[j] = filenames[i];
                        j++;
                    } else if (Long.valueOf(strs) < Long.valueOf(date)) {
                        if (flag1) {
                            s = strs;
                            str1 = filenames[i];
                            System.out.println("进入比较");
                            flag1 = false;
                        }
                        if (Long.valueOf(s) < Long.valueOf(strs)) {
                            str1 = filenames[i];
                            s = strs;
                            System.out.println("进入比较");
                        }
                    }
                }
                if("02".equals(strs1[2])) {
                    if (strs.equals(date)) {
                        st2[k] = filenames[i];
                        k++;
                    } else if (Long.valueOf(strs) < Long.valueOf(date)) {
                        if (flag2) {
                            s = strs;
                            str2 = filenames[i];
                            flag2 = false;
                        }
                        System.out.println(s);
                        System.out.println(strs);
                        if (Long.valueOf(s) < Long.valueOf(strs)) {
                            str2 = filenames[i];
                            s = strs;

                        }
                    }
                }
                }
            }
        if(st1 != null) {
        if(st2 != null) {
            int stLen1 = st1.length;// 保存第一个数组长度
            int stLen2 = st2.length;// 保存第二个数组长度
            st1 = Arrays.copyOf(st1, stLen1 + stLen2);// 扩容
            System.arraycopy(st2, 0, st1, st1.length, st2.length);// 将第二个数组与第一个数组合并
            return st1;
        }else{
            if(str2 != null) {
                int stLen1 = st1.length;// 保存第一个数组长度
                st1 = Arrays.copyOf(st1, stLen1 + 1);// 扩容
                st1[stLen1] = str2;
                return st1;
            }return st1;
        }
    }else{
        if(st2 != null) {
            if(str1 != null) {

                int stLen2 = st2.length;// 保存第一个数组长度
                st1 = Arrays.copyOf(st2, stLen2 + 1);// 扩容
                st1[0] = str1;
                System.arraycopy(st2, 0, st1, st1.length, st2.length);
                return st1;
            }
            System.out.println("节点1没有发现日志文件！" + "节点二日志文件为：" + st2);
            return st2;
        }else{
            if(str1 != null){
                if(str2 != null) {
                    st1 = new String[2];
                    st1[0] = str1;
                    st1[1] = str2;
                    return st1;
                } else{
                    st1 = new String[1];
                    st1[0] = str1;
                    return st1;
                }
            }else{
                if(str2 != null) {
                    st1 = new String[1];
                    st1[0] = str2;
                    return st1;
                } else{
                    System.out.println("节点1没有发现日志文件！"+"节点2没有发现日志文件！");
                    return st1;
                }
            }
        }
    }

    }


    /**
     * 查找在某个时间段内生成的日志文件
     * @param date1
     * @param date2
     * @param node
     * @param path
     * @param conn
     * @return
     */
    public String[] getNeeedFileName(Date date1,Date date2,Node node,String path,Connection conn) {//date1至date2之间的文件名
        getAllFileUtil = new GetAllFileUtil();

        String[] filenames = getAllFileUtil.getFile(path,conn);//获取指定目录下的所有文件
        String[] strs = new String[30];//取每个文件名中的日期
        String[] str = new String[30];//取符合日期的文件名
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String dat1 = df.format(date1);
        String dat2 = df.format(date2);
        int j =0;
        for (int i = 0; i < filenames.length; i++) {
            if(filenames[i]!=null) {
                String[] filename = filenames[i].split(String.valueOf('_'));//取每个文件名称切割后的字符串数组
                strs[i] = filename[2];
                if (Integer.valueOf(strs[i]) >= Integer.valueOf(dat1) && Integer.valueOf(strs[i]) <= Integer.valueOf(dat2)) {
                    str[j] = filenames[i];
                    j++;
                }
            }
        }
        if(str != null) {
            System.out.println(str);
            return str;
        }else{
            System.out.println("Can not found file!!");
            return null;
        }
    }
}
