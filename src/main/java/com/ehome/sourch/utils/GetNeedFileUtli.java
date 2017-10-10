package com.ehome.sourch.utils;

import com.ehome.sourch.pojo.Node;

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
    public String getNeeedFileName(Node node,String path){//取出最近的日志文件名
        getAllFileUtil = new GetAllFileUtil(node,path);

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

                    }

                }
            }
        }
        return str;
    }

    /**
     * 得到指定日期内生成的日志文件的名称
     * @param date1
     * @param node
     * @param path
     * @return
     */
    public String getNeeedFileName(String date1,Node node,String path){//取出指定日期的文件名
        getAllFileUtil = new GetAllFileUtil(node,path);

        String[] filenames = getAllFileUtil.getFile();//获取指定目录下的所有文件
        String[] strs = new String[30];//取每个文件名中的日期
        String str=null;//取符合日期的文件名
        for(int i=0; i<filenames.length; i++){
            String[] filename = filenames[i].split(String.valueOf('_'));//取每个文件名称切割后的字符串数组
            strs[i] = filename[1] + filename[2].split(".out");
            if(strs[i].equals(date1)) {
                str = filenames[i];
            }
        }
        return str;
    }

    public String[] getNeeedFileName(String date1,String data2,Node node,String path) {//date1至date2之间的文件名
        getAllFileUtil = new GetAllFileUtil(node,path);

        String[] filenames = getAllFileUtil.getFile();//获取指定目录下的所有文件
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
