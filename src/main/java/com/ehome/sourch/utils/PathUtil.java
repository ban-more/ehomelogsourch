package com.ehome.sourch.utils;

import com.ehome.sourch.pojo.Node;

/**
 * 拼接字符获取日志文件的路径
 * Created by wzw on 2017/10/10.
 */
public class PathUtil {

    public String getPath1(String nodename){

        return "/weblogic/log/" + nodename + "/";
    }

    public Node getPath(Node node){

        if(node.getPath1() == null||"".equals(node.getPath1())){
            if("srv1".equals(node.getNodename1())) {
                 node.setPath1(this.getPath1("Node_A"));
            }
        }
        if(node.getPath2() == null||"".equals(node.getPath2())){
            if("srv2".equals(node.getNodename2())) {
                node.setPath2(this.getPath1("Node_B"));
            }
        }

        return node;
    }
}
