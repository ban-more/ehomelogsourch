package com.ehome.sourch.pojo;

/**
 * 服务器实体类
 * Created by wzw on 2017/9/28.
 */
public class Node {
    private String ip;
    private int port;
    private String username;
    private String password;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
