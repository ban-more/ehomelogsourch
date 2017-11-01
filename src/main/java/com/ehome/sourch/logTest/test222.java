package com.ehome.sourch.logTest;

import ch.ethz.ssh2.Connection;
import com.ehome.sourch.pojo.Node;
import com.ehome.sourch.utils.GetFileLineUtil;
import com.ehome.sourch.utils.GetServerMessageUtil;
import com.ehome.sourch.utils.NodeConnectUtil;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

public class test222 {

    public static void main(String [] args) {
        GetServerMessageUtil getServerMessageUtil = new GetServerMessageUtil();
        List<Node> nodes = getServerMessageUtil.getNodes();
        String file = "/weblogic/log/Node_A/e-srv01-02_20170905_220551.out";
        NodeConnectUtil nodeConnectUtil = new NodeConnectUtil();
        try {
            Connection conn = nodeConnectUtil.getConnection(nodes.get(0));
            GetFileLineUtil getFileLineUtil = new GetFileLineUtil();
            String line = getFileLineUtil.getFileLine(file,conn);

            System.out.print(file +"行数为："+line);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
