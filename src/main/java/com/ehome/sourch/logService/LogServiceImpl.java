package com.ehome.sourch.logService;

import ch.ethz.ssh2.Connection;
import com.alibaba.fastjson.JSON;
import com.ehome.sourch.logDao.LogDaoImpl;
import com.ehome.sourch.pojo.Log;
import com.ehome.sourch.pojo.Node;
import com.ehome.sourch.utils.GetThreadPoolUtil;
import com.ehome.sourch.utils.NodeConnectUtil;
import com.ehome.sourch.utils.PathUtil;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by wzw on 2017/9/26.
 */
public class LogServiceImpl{

    private LogDaoImpl logDao = new LogDaoImpl();
    private GetThreadPoolUtil getThreadPoolUtil = new GetThreadPoolUtil();


    public String  findLogByNew(List<Node> nodes, String keyword,CountDownLatch _latch) throws IOException, InterruptedException {
        ThreadPoolExecutor pool = getThreadPoolUtil.getThreadPool();
        List<Log> logs = new ArrayList<Log>();
        int j=0;
        MyTask[] m = new MyTask[nodes.size()+1];
        for(int i=0; i < nodes.size(); i++){
            if(nodes.get(i) !=null) {
//                NodeConnectUtil nodeConnectUtil = new NodeConnectUtil();
//                Connection conn = nodeConnectUtil.getConnection(nodes.get(i));
//                PathUtil pathUtil= new PathUtil();
//                Node node = pathUtil.getPath(nodes.get(i));
//                Log log1 = logDao.findLogByNew(node, node.getNodename1(), node.getPath1(), keyword,conn);
//                Log log2 = logDao.findLogByNew(node, node.getNodename2(), node.getPath2(), keyword,conn);
//                conn.close();
//                System.out.println("连接已关闭");
//                logs.add(log1);
//                logs.add(log2);

                 m[j]= new MyTask(nodes.get(i),keyword,_latch);
                pool.execute(m[j]);
                j++;
            }
        }
        for(int k = 0; k<=j; k++ ){
            if(m[k] != null) {
                logs.addAll(m[k].getLogs());
            }
        }

        pool.shutdown();
        String jsonStr = JSON.toJSONString(logs);
        return jsonStr;
    }


    public String findAllLog(List<Node> nodes, String keyword) throws IOException {
        List<Log> logs = new ArrayList<Log>();


        for(int i=0; i < nodes.size(); i++){
            if(nodes.get(i) !=null) {
                NodeConnectUtil nodeConnectUtil = new NodeConnectUtil();
                Connection conn = nodeConnectUtil.getConnection(nodes.get(i));
                PathUtil pathUtil= new PathUtil();
                Node node = pathUtil.getPath(nodes.get(i));

                Log log1 = logDao.findAllLog(node, node.getNodename1(), node.getPath1(), keyword,conn);
                Log log2 = logDao.findAllLog(node, node.getNodename2(), node.getPath2(), keyword,conn);
                conn.close();
                System.out.println("连接已关闭");
                logs.add(log1);
                logs.add(log2);
            }
        }
        String jsonStr = JSON.toJSONString(logs);
        return jsonStr;
    }

    public String findAllLogByDate(Date date1, List<Node> nodes, String keyword) throws ParseException, IOException {
        List<Log> logs = new ArrayList<Log>();

        for(int i=0; i < nodes.size(); i++){
            if(nodes.get(i) !=null) {
                NodeConnectUtil nodeConnectUtil = new NodeConnectUtil();
                Connection conn = nodeConnectUtil.getConnection(nodes.get(i));
                PathUtil pathUtil= new PathUtil();
                Node node = pathUtil.getPath(nodes.get(i));

                Log log1 = logDao.findAllLogByDate(date1,node, node.getNodename1(), node.getPath1(), keyword,conn);
                Log log2 = logDao.findAllLogByDate(date1,node, node.getNodename2(), node.getPath2(), keyword,conn);
                conn.close();
                System.out.println("连接已关闭");
                logs.add(log1);
                logs.add(log2);
            }
        }
        String jsonStr = JSON.toJSONString(logs);
        return jsonStr;
    }

    public String findLogByNewByDate(Date date1, List<Node> nodes, String keyword) throws ParseException, IOException {
        List<Log> logs = new ArrayList<Log>();

        for(int i=0; i < nodes.size(); i++){
            if(nodes.get(i) !=null) {
                NodeConnectUtil nodeConnectUtil = new NodeConnectUtil();
                Connection conn = nodeConnectUtil.getConnection(nodes.get(i));
                PathUtil pathUtil= new PathUtil();
                Node node = pathUtil.getPath(nodes.get(i));
                Log log1 = logDao.findLogByNewByDate(date1,node, node.getNodename1(), node.getPath1(), keyword,conn);
                Log log2 = logDao.findLogByNewByDate(date1,node, node.getNodename2(), node.getPath2(), keyword,conn);
                conn.close();
                System.out.println("连接已关闭");
                logs.add(log1);
                logs.add(log2);
            }
        }
        String jsonStr = JSON.toJSONString(logs);
        return jsonStr;
    }


    public String findLogByNewByDate(Date date1, Date date2, List<Node> nodes, String keyword) throws ParseException, IOException {
        List<Log> logs = new ArrayList<Log>();

        for(int i=0; i < nodes.size(); i++){
            if(nodes.get(i) !=null) {
                NodeConnectUtil nodeConnectUtil = new NodeConnectUtil();
                Connection conn = nodeConnectUtil.getConnection(nodes.get(i));
                PathUtil pathUtil= new PathUtil();
                Node node = pathUtil.getPath(nodes.get(i));
                List<Log> log1 = logDao.findLogByNewByDate(date1,date2,node, node.getNodename1(), node.getPath1(), keyword,conn);
                List<Log> log2 = logDao.findLogByNewByDate(date1,date2,node, node.getNodename2(), node.getPath2(), keyword,conn);
                conn.close();
                System.out.println("连接已关闭");

                logs.addAll(log1);
                logs.addAll(log2);
            }
        }
        String jsonStr = JSON.toJSONString(logs);
        return jsonStr;
    }

    public String findLogByLine(Node node,String nodename,String keyword, String file, int line) throws IOException {

        NodeConnectUtil nodeConnectUtil = new NodeConnectUtil();
        Connection conn = nodeConnectUtil.getConnection(node);
        Log log = logDao.findLogByLine(node, nodename,keyword,file,line,conn);
        conn.close();
        System.out.println("连接已关闭");
        String jsonStr = JSON.toJSONString(log);
        return jsonStr;

    }


}