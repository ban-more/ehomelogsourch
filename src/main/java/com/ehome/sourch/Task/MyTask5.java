package com.ehome.sourch.Task;

import ch.ethz.ssh2.Connection;
import com.ehome.sourch.logDao.LogDaoImpl;
import com.ehome.sourch.pojo.Log;
import com.ehome.sourch.pojo.Node;
import com.ehome.sourch.utils.NodeConnectUtil;
import com.ehome.sourch.utils.PathUtil;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MyTask5 implements Runnable {

    private Node node = new Node();
    private String keyword;
    private Date date1;
    private List<Log> logs;
    private CountDownLatch _latch;

    public List<Log> getLogs() {
        return logs;
    }

    private LogDaoImpl logDao = new LogDaoImpl();
    public MyTask5(Node node, String keyword, Date date1, CountDownLatch _latch) {
        this.node = node;
        this.keyword = keyword;
        this.date1 = date1;
        this._latch = _latch;
    }


    @Override

    public void run() {

        try {
                logs = new ArrayList<Log>();
                NodeConnectUtil nodeConnectUtil = new NodeConnectUtil();
                Connection conn = nodeConnectUtil.getConnection(node);
                if(conn != null) {
                    PathUtil pathUtil = new PathUtil();
                    Node node1 = pathUtil.getPath(node);
                    Log log1 = logDao.findLogByNewByDate(date1, node1, node1.getNodename1(), node1.getPath1(), keyword, conn);
                    Log log2 = logDao.findLogByNewByDate(date1, node1, node1.getNodename2(), node1.getPath2(), keyword, conn);
                    conn.close();
                    System.out.println("连接已关闭");
                    if (log1 != null) {
                        logs.add(log1);
                    }
                    if (log2 != null) {
                        logs.add(log2);
                    }
                }else{
                    logs = new ArrayList<Log>();
                    Log log3 = new Log();
                    log3.setNode(node);
                    log3.setKeyword("服务器链接异常请及时查看服务器情况！！！");
                    logs.add(log3);
                }
//                System.out.println(log1.getKeyword()+log1.getMessages());
//                System.out.println(log2.getKeyword()+log2.getMessages());
                 _latch.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}