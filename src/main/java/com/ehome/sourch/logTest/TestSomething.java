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

/**
 * Created by wzw on 2017/9/29.
 */
public class TestSomething {

    public static void main(String[] args) throws IOException, ParseException {



        GetServerMessageUtil getServerMessageUtil = new GetServerMessageUtil();
        List<Node> nodes = getServerMessageUtil.getNodes();

        String file = "/weblogic/log/Node_A/e-srv01-01_20170905_220550.out";
        String keyword = "Error";
        LogServiceImpl logService = new LogServiceImpl();

        Log log = new Log();

//        NodeConnectUtil nodeConnectUtil = new NodeConnectUtil();
//
//        Connection conn = nodeConnectUtil.getConnection(node);

        String date1 = "2017-07-10";
        String date2 = "2017-07-12";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date dat1 = df.parse(date1);
        Date dat2 = df.parse(date2);

        String nodename = "Node_A";
        int line = 15442;
//       List<Log> logs = logService.findAllLog(nodes,keyword);//测试查找新生成的日志文件并且查
//        log = logDao.findLogByNewByDate(dat1,node,nodename,keyword,conn);//测试按照指定日期查找的日志文件并取出最新的一行日志
           String logString =  logService.findLogByNew(nodes,keyword);//测试最新生成的日志文件并且取出最新的一行日志
//        log = logDao.findLogByLine(file,nodename,line,conn);
//        List<Log> logs = logDao.findLogByNewByDate(dat1,dat2,node,nodename,keyword);
//        Map<Integer,String> map = new HashMap<Integer, String>();
//        map = log.getMessages();
//        for (Map.Entry<Integer, String> entry : map.entrySet()) {
//            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
//        }


//        List<Log> logs = new ArrayList<Log>();

        List<Log> logs = JSON.parseArray(logString, Log.class);
        Map<Integer,String> map = new HashMap<Integer, String>();
        Iterator it = logs.iterator();
        while(it.hasNext()){
            log = (Log) it.next();
            map = log.getMessages();
            Node node = log.getNode();
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                System.out.println("| IP:" + node.getIp() + " | Port:" + node.getPort() + " | NodeName:" + log.getNodename() + " | KeyWord:" + log.getKeyword() + " | fileName:" + log.getFilename());

                System.out.println("| Line: " + entry.getKey() + " | message: " + entry.getValue());
            }

        }
//



    }

}
