package com.ehome.sourch.logController;

import com.ehome.sourch.pojo.Node;
import com.ehome.sourch.utils.GetAllFileUtil;

/**
 * Created by wzw on 2017/9/29.
 */
public class TestConnect {
    public static void main(String[] args){
        Node node = new Node();
        node.setIp("hadoop04");
        node.setPort(22);
        node.setUsername("root");
        node.setPassword("wang1509");
        String path  = "/usr/local/";
        GetAllFileUtil getAllFileUtil = new GetAllFileUtil(node, path);

        String[] filenames = getAllFileUtil.getFile();//获取指定目录下的所有文件
        String[] strs = new String[30];//取每个文件名中的日期
        String str=filenames[0];//取日期最大的文件名
        String st = null;
        for(int i=0; i<filenames.length; i++) {
            if (filenames[i] != null){
                String[] filename = filenames[i].split(String.valueOf('_'));//取每个文件名称切割后的字符串数组
                String[] strs2 = new String[2];
                strs2 = filename[2].split(".out");
                strs[i] = filename[1] + strs2[0];
                if(i==0){
                    st =  strs[i];
                }
                if (i > 0) {
                    System.out.println(Long.valueOf(st) < Long.valueOf(strs[i]));
                        if (Long.valueOf(st) < Long.valueOf(strs[i])) {
                            str = filenames[i];
                            st = strs[i];
                            System.out.println("************************");
                            System.out.print("*" + filenames[i] + "*");
                            System.out.println("************************");
                        }

                }
            }
        }
        System.out.println(str);
    }
}
