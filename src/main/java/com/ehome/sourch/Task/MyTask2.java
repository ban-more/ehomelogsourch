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
            if(conn != null) {
                PathUtil pathUtil = new PathUtil();
                Node node1 = pathUtil.getPath(node);
                List<Log> logs1 = logDao.findAllLog(node1, keyword, conn);

                conn.close();
                System.out.println("连接已关闭");
                if (logs1 != null) {
                    logs.addAll(logs1);
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
        }

    }


}
