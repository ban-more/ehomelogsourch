package com.ehome.sourch.utils;

/**
 * 拼接字符获取日志文件的路径
 * Created by wzw on 2017/10/10.
 */
public class PathUtil {

    public String getPath(String nodename){

        return "/weblogic/log/" + nodename + "/";
    }
}
