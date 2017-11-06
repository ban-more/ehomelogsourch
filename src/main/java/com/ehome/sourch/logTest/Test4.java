package com.ehome.sourch.logTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test4 {
    public static void main(String [] args) throws ParseException {
        String p = "2017-11-03 13:49:10";
        DateFormat df1 = new SimpleDateFormat("yyyy-M-d");//将日期转换成字符串类型
        Date date = df1.parse(p);
//       DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = df1.format(date);
        System.out.println(date1);
//        String keyword = "error";
//        String file = "filename";
//        String date1 = "201-10-08";
//        String date2 = "2017-9-9";
//        String date = "grep -n '"+keyword+"' "+file+" | grep '"+date1+"\\|"+date2+"'";
//        System.out.println(date);
    }}
