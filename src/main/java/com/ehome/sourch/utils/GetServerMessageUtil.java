package com.ehome.sourch.utils;

import com.ehome.sourch.pojo.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 读取配置文件中的信息
 */
public class GetServerMessageUtil {

    /**
     * 取出日志文件信息的文件文件名必须是ServerMessage的properties
     * @return
     */
    public List<Node> getNodes(){
        PropertiesUtil.loadFile("ServerMessage");
        List<Node> nodes = new ArrayList<Node>();
        for(int i=1; i <= Integer.valueOf(PropertiesUtil.getPropertyValue("servernum")); i++) {
            Node node1 = new Node();

            node1.setIp(PropertiesUtil.getPropertyValue("ip"+i));
            node1.setPort(Integer.valueOf(PropertiesUtil.getPropertyValue("port"+i)));
            node1.setUsername(PropertiesUtil.getPropertyValue("username"+i));
            node1.setPassword(PropertiesUtil.getPropertyValue("password"+i));
           if( PropertiesUtil.getPropertyValue("nodename"+i+"-1") != null&&!"".equals(PropertiesUtil.getPropertyValue("nodename"+i+"-1"))){
                node1.setNodename1(PropertiesUtil.getPropertyValue("nodename"+i+"-1"));
                if (PropertiesUtil.getPropertyValue("path" + i + "-1") != null) {
                    node1.setPath1(PropertiesUtil.getPropertyValue("path" + i + "-1"));
                }
            }
            if(PropertiesUtil.getPropertyValue("nodename"+i+"-2") != null&&!"".equals(PropertiesUtil.getPropertyValue("nodename"+i+"-2"))) {
                node1.setNodename2(PropertiesUtil.getPropertyValue("nodename" + i + "-2"));
                if (PropertiesUtil.getPropertyValue("path" + i + "-2") != null) {
                    node1.setPath2(PropertiesUtil.getPropertyValue("path" + i + "-2"));
                }
            }

            nodes.add(node1);
        }

        return nodes;
    }
}
