package com.ehome.sourch.logTest;

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
        String str = "测试字符转换 hello word"; //默认环境，已是UTF-8编码
        try {
            String strGBK = URLEncoder.encode(str, "GBK");
            System.out.println(strGBK);
            String strUTF8 = URLDecoder.decode(str, "UTF-8");
            System.out.println(strUTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        String s = "王";
//        System.out.println(s);
//        byte[] b = s.getBytes("GBK");
//        String n = new String(b,"UTF-8");
//        System.out.println(n);

    }}
