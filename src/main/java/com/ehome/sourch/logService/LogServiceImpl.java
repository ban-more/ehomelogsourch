package com.ehome.sourch.logService;

import ch.ethz.ssh2.Connection;
import com.ehome.sourch.logDao.LogDao;
import com.ehome.sourch.logDao.LogDaoImpl;
import com.ehome.sourch.pojo.Log;
import com.ehome.sourch.pojo.Node;
import com.ehome.sourch.utils.NodeConnectUtil;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wzw on 2017/9/26.
 */
public class LogServiceImpl implements LogService{

    private LogDao logDao = new LogDaoImpl();

    public List<Log> findLogByNew(List<Node> nodes, String keyword) throws IOException {
        List<Log> logs = new ArrayList<Log>();

        for(int i=0; i < nodes.size(); i++){
            if(nodes.get(i) !=null) {
                NodeConnectUtil nodeConnectUtil = new NodeConnectUtil();
                Connection conn = nodeConnectUtil.getConnection(nodes.get(i));
                Log log1 = logDao.findLogByNew(nodes.get(i), keyword,conn);
                conn.close();
                System.out.println("连接已关闭");
                logs.add(log1);
            }
        }
        return logs;
    }


    public List<Log> findAllLog(List<Node> nodes, String keyword) throws IOException {
        List<Log> logs = new ArrayList<Log>();


        for(int i=0; i < nodes.size(); i++){
            if(nodes.get(i) !=null) {
                NodeConnectUtil nodeConnectUtil = new NodeConnectUtil();
                Connection conn = nodeConnectUtil.getConnection(nodes.get(i));
                Log log1 = logDao.findAllLog(nodes.get(i), keyword,conn);
                conn.close();
                System.out.println("连接已关闭");
                logs.add(log1);
            }
        }
        return logs;
    }

    public List<Log> findAllLogByDate(Date date1, List<Node> nodes, String keyword) throws ParseException, IOException {
        List<Log> logs = new ArrayList<Log>();

        for(int i=0; i < nodes.size(); i++){
            if(nodes.get(i) !=null) {
                NodeConnectUtil nodeConnectUtil = new NodeConnectUtil();
                Connection conn = nodeConnectUtil.getConnection(nodes.get(i));
                Log log1 = logDao.findAllLogByDate(date1,nodes.get(i), keyword,conn);
                conn.close();
                System.out.println("连接已关闭");
                logs.add(log1);
            }
        }
        return logs;
    }

    public List<Log> findLogByNewByDate(Date date1, List<Node> nodes, String keyword) throws ParseException, IOException {
        List<Log> logs = new ArrayList<Log>();

        for(int i=0; i < nodes.size(); i++){
            if(nodes.get(i) !=null) {
                NodeConnectUtil nodeConnectUtil = new NodeConnectUtil();
                Connection conn = nodeConnectUtil.getConnection(nodes.get(i));
                Log log1 = logDao.findLogByNewByDate(date1,nodes.get(i), keyword,conn);
                conn.close();
                System.out.println("连接已关闭");
                logs.add(log1);
            }
        }
        return logs;
    }


    public List<Log> findLogByNewByDate(Date date1, Date date2, List<Node> nodes, String keyword) throws ParseException, IOException {
        List<Log> logs = new ArrayList<Log>();

        for(int i=0; i < nodes.size(); i++){
            if(nodes.get(i) !=null) {
                NodeConnectUtil nodeConnectUtil = new NodeConnectUtil();
                Connection conn = nodeConnectUtil.getConnection(nodes.get(i));
                List<Log> log1 = logDao.findLogByNewByDate(date1,date2,nodes.get(i), keyword,conn);
                conn.close();
                System.out.println("连接已关闭");

                logs.addAll(log1);
            }
        }
        return logs;
    }

    public Log findLogByLine(Node node, String file, int line) throws IOException {

        Log log = logDao.findLogByLine(node,file,line);
        return log;

    }


}