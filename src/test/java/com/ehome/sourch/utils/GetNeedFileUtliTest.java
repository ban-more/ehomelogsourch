package com.ehome.sourch.utils;

import com.ehome.sourch.pojo.Node;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Created by wzw on 2017/9/28.
 */
class GetNeedFileUtliTest {
    @Test
    void getNeeedFileName() {
        String path="";
        Node node = new Node();
        node.setIp("192.168.134.143");
        node.setPort(22);
        node.setUsername("root");
        node.setPassword("wang1509");
        NodeConnectUtil nodeConnectUtil = new NodeConnectUtil();
        try {
            nodeConnectUtil.getConnection(node,path);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}