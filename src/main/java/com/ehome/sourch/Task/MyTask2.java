package com.ehome.sourch.Task;

import ch.ethz.ssh2.Connection;
import com.ehome.sourch.logDao.LogDaoImpl;
import com.ehome.sourch.pojo.Log;
import com.ehome.sourch.pojo.Node;
import com.ehome.sourch.utils.NodeConnectUtil;
import com.ehome.sourch.utils.PathUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MyTask2 implements Runnable {

    private Node node = new Node();
    private String keyword;
    private List<Log> logs;
    private CountDownLatch _latch;

    public List<Log> getLogs() {
        return logs;
    }

    private LogDaoImpl logDao = new LogDaoImpl();
    public MyTask2(Node node, String keyword, CountDownLatch _latch) {
        this.node = node;
        this.keyword = keyword;
        this._latch = _latch;
    }


    @Override

    public void run() {

        try {
            logs = new ArrayList<Log>();
            NodeConnectUtil nodeConnectUtil = new NodeConnectUtil();
            Connection conn = nodeConnectUtil.getConnection(node);
            PathUtil pathUtil= new PathUtil();
            Node node1 = pathUtil.getPath(node);

            Log log1 = logDao.findAllLog(node1, node1.getNodename1(), node1.getPath1(), keyword,conn);
            Log log2 = logDao.findAllLog(node1, node1.getNodename2(), node1.getPath2(), keyword,conn);
            conn.close();
            System.out.println("连接已关闭");
            if(log1 != null) {
                logs.add(log1);
            }
            if(log2 != null) {
                logs.add(log2);
            }
//                System.out.println(log1.getKeyword()+log1.getMessages());
//                System.out.println(log2.getKeyword()+log2.getMessages());
            _latch.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}