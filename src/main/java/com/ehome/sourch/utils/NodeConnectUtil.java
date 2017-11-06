package com.ehome.sourch.utils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import com.ehome.sourch.pojo.Node;

import java.io.*;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 连接服务器
 * Created by wzw on 2017/9/27.
 */
public class NodeConnectUtil {

    public Connection getConnection(Node node){
        Connection conn = new Connection(node.getIp(), node.getPort());
        Session ssh = null;
        //连接到主机
        try {
            conn.connect();
        } catch (IOException e) {
            System.out.println("链接服务器失败！！");
            return null;
        }
        //使用用户名和密码校验
        boolean isconn = false;
        try {
            isconn = conn.authenticateWithPassword(node.getUsername(), node.getPassword());
        } catch (IOException e) {
            System.out.println("链接服务器异常！！");
            return null;
        }
        if (!isconn) {
            System.out.println("用户名称或者是密码不正确");
        } else {
            System.out.println("已经连接OK");

         }
        return conn;
    }
}