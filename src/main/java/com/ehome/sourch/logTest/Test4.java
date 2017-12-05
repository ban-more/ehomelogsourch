package com.ehome.sourch.logTest;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Test4 {
    public static void main(String [] args) throws ParseException, UnsupportedEncodingException {
//        String p = "2017-11-03 13:49:10";
//        DateFormat df1 = new SimpleDateFormat("yyyy-M-d");//将日期转换成字符串类型
//        Date date = df1.parse(p);
////       DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
//        String date1 = df1.format(date);
//        System.out.println(date1);
////        String keyword = "error";
////        String file = "filename";
////        String date1 = "201-10-08";
////        String date2 = "2017-9-9";
////        String date = "grep -n '"+keyword+"' "+file+" | grep '"+date1+"\\|"+date2+"'";
////        System.out.println(date);
//        String[] str1 = { "J", "a", "v", "a", "中" };
//        String[] str2 = { "如", "何", "把", "两", "个", "数", "组", "合", "并", "为",
//                "一", "个" };

//        int strLen1 = str1.length;// 保存第一个数组长度
//        int strLen2 = str2.length;// 保存第二个数组长度
//        str1 = Arrays.copyOf(str1, strLen1 + strLen2);// 扩容
//        System.arraycopy(str2, 0, str1, str1.length, str2.length);// 将第二个数组与第一个数组合并
//        System.out.println(Arrays.toString(str1));// 输出数组

//        String keyword = "wang select * from user!";//测试包含 * 格式的关键字分割方式
//        if(keyword.indexOf(" * ") != -1) {
//            String[] kd = keyword.split("\\*");
//            String keyword1 = kd[0] + " *";
//            String keyword2 = "* " + kd[1];
//            System.out.println(keyword1);
//            System.out.println(keyword2);
//            System.out.println(kd.length);
//        }
//        String name = "66:asadfsadfsdgsdfafdsdfsadfsad";
//        String[] n1 = name.split(":",2);
//        System.out.println(n1[0]);
//        System.out.println(n1[1]);
//        String cmd ="mkdir /weblogic/log/Node_A/王时撒打算分手大师的v.txt";
//        cmd = new String( cmd.getBytes("GBK") , "UTF-8");
//        System.out.println(cmd);
//        String str = "测试字符转换 hello word"; //默认环境，已是UTF-8编码
//        try {
//            String strGBK = URLEncoder.encode(str, "GBK");
//            System.out.println(strGBK);
//            String strUTF8 = URLDecoder.decode(str, "UTF-8");
//            System.out.println(strUTF8);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String s = "王";
//        System.out.println(s);
//        byte[] b = s.getBytes("GBK");
//        String n = new String(b,"UTF-8");
//        System.out.println(n);
        String[] filenames = new String[30];
             filenames[0] = "/weblogic/log/Node_B/e-srv01-01_20170905_220550.out";
        String[] filenames2 = new String[30];
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
                    System.out.println("find srv01 node message!!!!");
                    if (j == 0) {
                        str1 = filenames[i];
                        System.out.println("The j is zeor!!!! "+str1);
                        st1 = strs;
                        j++;
                    }
                    if (j > 0) {
                        if (Long.valueOf(st1) < Long.valueOf(strs)) {
                            str1 = filenames[i];
                            System.out.println(str1);
                            st1 = strs;
                            j++;
                        }
                    }
                }
                if("02".equals(strs1[2])) {
                    System.out.println("find srv02 node message!!!!");
                    if (k == 0) {
                        str2 = filenames[i];
                        System.out.println("The k is zeor!!!! "+str2);
                        st2 = strs;
                        k++;
                    }
                    if (k > 0) {
                        if (Long.valueOf(st2) < Long.valueOf(strs)) {
                            str2 = filenames[i];
                            System.out.println(str2);
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

                 for(int i =0; i< filename.length;i++){
                     System.out.println(filename[i]);
                 }
            }else{
                filename[0] = str1;
                System.out.println("节点1日志文件为："+str1+"节点2没有发现日志文件！");
                for(int i =0; i< filename.length;i++){
                    System.out.println(filename[i]);
                }
            }
        }else{
            if(str2 != null) {
                filename[0] = str2;
                System.out.println("节点1没有发现日志文件！"+"节点2日志文件为："+str2);
                for(int i =0; i< filename.length;i++){
                    System.out.println(filename[i]);
                }
            }else{
                System.out.println("节点1没有发现日志文件！"+"节点2没有发现日志文件！");
                for(int i =0; i< filename.length;i++){
                    System.out.println(filename[i]);
                }
            }
        }
    }}
