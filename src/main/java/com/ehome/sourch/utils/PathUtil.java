package com.ehome.sourch.utils;

/**
 * Created by wzw on 2017/10/10.
 */
public class PathUtil {
    public String getPath(String nodename){
        return "/weblogic/log/" + nodename + "/";
    }
}
