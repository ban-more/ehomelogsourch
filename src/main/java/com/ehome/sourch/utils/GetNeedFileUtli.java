package com.ehome.sourch.utils;

/**
 * 得到所需的日志文件名
 * Created by wzw on 2017/9/27.
 */
public class GetNeedFileUtli {

    private GetAllFileUtil getAllFileUtil;
    /**
     * 得到最近的日志文件
     * @return
     */
    public String getNeeedFileName(){//取出最近的日志文件名
        getAllFileUtil = new GetAllFileUtil();

        String[] filenames = getAllFileUtil.getFile();//获取指定目录下的所有文件
        String[] filename = new String[0];//取每个文件名称切割后的字符串数组
        String[] strs = new String[0];//取每个文件名中的日期
        String str=filenames[0];//取日期最大的文件名
        String st = null;
        for(int i=0; i<filenames.length; i++){
           filename[i] = String.valueOf(filenames[i].split(String.valueOf('_')));
           strs[i] = filename[1] + filename[2].split(String.valueOf('.'));
           if(i!=0) {
               if(i==1) {
                   st = strs[i - 1];
               }
               if (Integer.valueOf(st) < Integer.valueOf(strs[i])) {
                   str = filenames[i];
                   st = strs[i];
               }
           }
        }
       return str;
    }
    public String getNeeedFileName(String date1){//取出指定日期的文件名
        getAllFileUtil = new GetAllFileUtil();

        String[] filenames = getAllFileUtil.getFile();//获取指定目录下的所有文件
        String[] filename = new String[0];//取每个文件名称切割后的字符串数组
        String[] strs = new String[0];//取每个文件名中的日期
        String str=null;//取日期最大的文件名
        for(int i=0; i<filenames.length; i++){
            filename[i] = String.valueOf(filenames[i].split(String.valueOf('_')));
            strs[i] = filename[1] + filename[2].split(String.valueOf('.'));
            if(strs[i].equals(date1)) {
                str = filenames[i];
            }
        }
        return str;
    }

    public String[] getNeeedFileName(String date1,String data2) {//date1至date2之间的文件名
        getAllFileUtil = new GetAllFileUtil();

        String[] filenames = getAllFileUtil.getFile();//获取指定目录下的所有文件
        String[] filename = new String[0];//取每个文件名称切割后的字符串数组
        String[] strs = new String[0];//取每个文件名中的日期
        String[] str = new String[0];//取日期最大的文件名
        int j =0;
        for (int i = 0; i < filenames.length; i++) {
            filename[i] = String.valueOf(filenames[i].split(String.valueOf('_')));
            strs[i] = filename[1] + filename[2].split(String.valueOf('.'));
            if (Integer.valueOf(strs[i]) > Integer.valueOf(date1)&&Integer.valueOf(strs[i]) < Integer.valueOf(data2)) {
                str[j] = filenames[i];
                j++;
            }
        }
        return str;
    }
}
