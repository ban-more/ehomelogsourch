package com.ehome.sourch.pojo;

import java.util.List;
import java.util.Map;

/**
 * Created by wzw on 2017/9/27.
 */
public class Log {

    private String nodename;
    private String keyword;

    private Map<Integer,String> messages;

    public Map<Integer, String> getMessages() {
        return messages;
    }

    public void setMessages(Map<Integer, String> messages) {
        this.messages = messages;
    }

    public String getNodename() {

        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }




}
