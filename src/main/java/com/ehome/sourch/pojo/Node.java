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
    private String path1;
    private String path2;
    private String nodename1;
    private String nodename2;

    public String getPath1() {
        return path1;
    }

    public void setPath1(String path1) {
        this.path1 = path1;
    }

    public String getPath2() {
        return path2;
    }

    public void setPath2(String path2) {
        this.path2 = path2;
    }

    public String getNodename1() {
        return nodename1;
    }

    public void setNodename1(String nodename1) {
        this.nodename1 = nodename1;
    }

    public String getNodename2() {
        return nodename2;
    }

    public void setNodename2(String nodename2) {
        this.nodename2 = nodename2;
    }

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
