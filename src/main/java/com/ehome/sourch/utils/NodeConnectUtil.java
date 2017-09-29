package com.ehome.sourch.utils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import com.ehome.sourch.pojo.Node;

import java.io.*;

/**
 * Created by wzw on 2017/9/27.
 */
public class NodeConnectUtil {

    public Connection getConnection(Node node, String path) throws IOException {
        Connection conn = new Connection(node.getIp(), node.getPort());
        Session ssh = null;
        //连接到主机
        try {
            conn.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //使用用户名和密码校验
        boolean isconn = conn.authenticateWithPassword(node.getUsername(), node.getPassword());
        if (!isconn) {
            System.out.println("用户名称或者是密码不正确");
        } else {
            System.out.println("已经连接OK");


//            File folder = new File(path);
//            GetNeedFileUtli getNeedFileUtli = new GetNeedFileUtli();
//
//            String
////            if (!folder.exists()) {
////                folder.mkdir();
////            }

        }
//        SCPClient clt = conn.createSCPClient();
//        ssh = conn.openSession();
//        ssh.execCommand("find " + path + "-name '*.txt'");
//        InputStream is = new StreamGobbler(ssh.getStdout());
//        return (new InputStreamReader(is));
        return conn;
    }
}