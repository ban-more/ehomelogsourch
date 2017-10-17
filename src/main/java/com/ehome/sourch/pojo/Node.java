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
    private String path;
   private String nodemane;

    public String getNodemane() {
        return nodemane;
    }

    public void setNodemane(String nodemane) {
        this.nodemane = nodemane;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
