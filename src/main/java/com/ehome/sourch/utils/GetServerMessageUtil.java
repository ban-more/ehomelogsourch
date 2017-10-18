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
            node1.setNodemane(PropertiesUtil.getPropertyValue("nodename"+i+"-1"));
            if( PropertiesUtil.getPropertyValue("path"+i+"-1") != null) {
                node1.setPath(PropertiesUtil.getPropertyValue("path" + i+"-1"));
            }
            Node node2 = new Node();

            node2.setIp(PropertiesUtil.getPropertyValue("ip"+i));
            node2.setPort(Integer.valueOf(PropertiesUtil.getPropertyValue("port"+i)));
            node2.setUsername(PropertiesUtil.getPropertyValue("username"+i));
            node2.setPassword(PropertiesUtil.getPropertyValue("password"+i));
            node2.setNodemane(PropertiesUtil.getPropertyValue("nodename"+i+"-2"));
            if( PropertiesUtil.getPropertyValue("path"+i+"-2") != null) {
                node2.setPath(PropertiesUtil.getPropertyValue("path" + i+"-2"));
            }

            nodes.add(node1);
            nodes.add(node2);
        }

        return nodes;
    }
}
