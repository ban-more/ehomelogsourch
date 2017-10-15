package com.ehome.sourch.logController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wzw on 2017/10/11.
 */
public class Testttttt {

    public static void main(String [] args){

        String date1 = "20171011";
        DateFormat df = new SimpleDateFormat("yyyyMMdd");

        try {
            Date dt1 = df.parse(date1);
            String dt2 = df.format(dt1);
            System.out.println(dt1);
            System.out.println(dt2);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

