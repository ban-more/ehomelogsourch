package com.ehome.sourch.pojo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 日志实体类
 * Created by wzw on 2017/9/27.
 */
public class Log {

    private String keyword;
    private String filename;
    private Node node;
    private String nodename;
    private LinkedHashMap<Long,String> messages;

    public LinkedHashMap<Long, String> getMessages() {
        return messages;
    }

    public void setMessages(LinkedHashMap<Long, String> messages) {
        this.messages = messages;
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


}
