package com.ehome.sourch.logTest;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.ehome.sourch.pojo.Log;
import com.ehome.sourch.pojo.Node;
import com.ehome.sourch.utils.GetServerMessageUtil;
import com.ehome.sourch.utils.NodeConnectUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test3 {
    public static void main(String [] args){
        Session ssh = null;
        Log log = new Log();
        GetServerMessageUtil getServerMessageUtil = new GetServerMessageUtil();
        List<Node> nodes = getServerMessageUtil.getNodes();

        try {
            NodeConnectUtil nodeConnectUtil = new NodeConnectUtil();
            Connection conn = nodeConnectUtil.getConnection(nodes.get(0));
            String file = "/weblogic/log/Node_A/e-srv03-02_20170923_103055.out";
            ssh = conn.openSession();
            ssh.execCommand("cat " + file);
            InputStream stdout = new StreamGobbler(ssh.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout,"GBK"));
            String line = br.readLine();
            System.out.println(line);
            conn.close();
                System.out.println("连接已关闭");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
