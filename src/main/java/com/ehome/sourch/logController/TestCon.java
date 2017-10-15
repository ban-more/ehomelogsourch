package com.ehome.sourch.logController;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

import java.io.IOException;

/**
 * Created by wzw on 2017/9/29.
 */
public class TestCon {
    public static void main(String[] args) {
        Connection conn = new Connection("hadoop04", 22);
        Session ssh = null;
        //连接到主机
        try {
            conn.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //使用用户名和密码校验
        boolean isconn = false;
        try {
            isconn = conn.authenticateWithPassword("root", "wang1509");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!isconn) {
            System.out.println("用户名称或者是密码不正确");
        } else {
            System.out.println("已经连接OK");

        }
    }
}
