package com.ehome.sourch.logTest;

import com.alibaba.fastjson.JSON;
import com.ehome.sourch.logService.LogServiceImpl;
import com.ehome.sourch.pojo.Log;
import com.ehome.sourch.pojo.Node;
import com.ehome.sourch.utils.GetServerMessageUtil;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * Created by wzw on 2017/9/29.
 */
public class TestSomething {

    public static void main(String[] args) throws IOException, ParseException, InterruptedException {



        GetServerMessageUtil getServerMessageUtil = new GetServerMessageUtil();
        List<Node> nodes = getServerMessageUtil.getNodes();

        String file = "/weblogic/log/Node_A/e-srv01-01_20170905_220550.out";
        String keyword = "Error";
        LogServiceImpl logService = new LogServiceImpl();

        Log log = new Log();

//        NodeConnectUtil nodeConnectUtil = new NodeConnectUtil();
//
//        Connection conn = nodeConnectUtil.getConnection(node);

        String date1 = "2017-07-11";
        String date2 = "2017-10-12";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date dat1 = df.parse(date1);
        Date dat2 = df.parse(date2);

        String nodename = "Node_A";
        int line = 15442;
//        String logString = logService.findAllLog(nodes,keyword,_latch);//测试查找新生成的日志文件并且查
//         String logString = logService.findLogByNewByDate(dat1,nodes,keyword);//测试按照指定日期查找的日志文件并取出最新的一行日志
           String logString =  logService.findLogByNew(nodes,keyword);//测试最新生成的日志文件并且取出最新的一行日志
//        String logString = logService.findAllLogByDate(dat1,nodes,keyword,_latch);
//          String logString = logService.findLogByNewByDate(dat1,dat2,nodes,keyword);
//        Map<Integer,String> map = new HashMap<Integer, String>();
//        map = log.getMessages();
//        for (Map.Entry<Integer, String> entry : map.entrySet()) {
//            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
//        }


//        List<Log> logs = new ArrayList<Log>();
        Thread.sleep(1000);
        List<Log> logs = JSON.parseArray(logString, Log.class);
        Map<Long,String> map = new HashMap<Long, String>();
        Iterator it = logs.iterator();
        while(it.hasNext()){
            log = (Log) it.next();
            Node node = log.getNode();
            if(log.getMessages()!=null) {
                map = log.getMessages();
                for (Map.Entry<Long, String> entry : map.entrySet()) {
                    System.out.println("| IP:" + node.getIp() + " | Port:" + node.getPort() + " | NodeName:" + log.getNodename() + " | KeyWord:" + log.getKeyword() + " | fileName:" + log.getFilename());

                    System.out.println("| Line: " + entry.getKey() + " | message: " + entry.getValue());
                }
            }
        }
//



    }

}
