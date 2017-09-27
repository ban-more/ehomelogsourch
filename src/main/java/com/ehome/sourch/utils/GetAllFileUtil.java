package com.ehome.sourch.utils;

import java.io.File;
import java.util.Date;

/**
 * 获取指定目录下的所有日志文件
 * Created by wzw on 2017/9/26.
 */
public class GetAllFileUtil {

    private static String path;

    public static String[] getFile() {

        path = "";
        // 获得指定文件对象
        File file = new File(path);
        // 获得该文件夹内的所有文件
        File[] array = file.listFiles();

        String[] filenames = new String[0];
        int o = 0;
        for (int i = 0; i < array.length; i++) {

            if (array[i].isFile()){
                if(array[i].getName().indexOf(".out")!= -1){
                    filenames[o] = array[i].getName();
                    o++;
                }


            }

        }
        return filenames;
    }
}


