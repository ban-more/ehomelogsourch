package com.ehome.sourch.logController;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.ehome.sourch.logDao.LogDaoImpl;
import com.ehome.sourch.pojo.Log;
import com.ehome.sourch.pojo.Node;
import com.ehome.sourch.utils.NodeConnectUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wzw on 2017/9/29.
 */
public class TestSomething {

    public static void main(String[] args) throws IOException, ParseException {
//        String l1="e-srv05-02_20170711_215405.out";
//         String[] l2 = l1.split("_");
//         for (int i=0; i < l2.length; i++){
//             System.out.println(l2[i]);
//         }

//        String l1 = "20170908123245";
//        String l2 = "20170908124245";
//        if(Long.valueOf(l1) < Long.valueOf(l2)) {
//            System.out.println(Long.valueOf(l2));
//        }else{
//            System.out.println(Long.valueOf(l1));
//        }
//

        Node node = new Node();

        String keyword = "1191640000000330";
        String file = "/weblogic/log/Node_A/e-srv01-01_20170905_220550.out";
        node.setIp("hadoop04");
        node.setPort(22);
        node.setUsername("root");
        node.setPassword("wang1509");

        LogDaoImpl logDao = new LogDaoImpl();

        Log log = new Log();

        NodeConnectUtil nodeConnectUtil = new NodeConnectUtil();

        Connection conn = nodeConnectUtil.getConnection(node);

        String date = "2017-09-05";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = df.parse(date);

        String nodename = "Node_A";
        int line = 15442;
//        log = logDao.findAllLog(node,nodename,keyword,conn);
//        log = logDao.findLogByDate(node,nodename,keyword,conn);
        log = logDao.findLogByLine(file,nodename,line,conn);
        Map<Integer,String> map = new HashMap<Integer, String>();
        map = log.getMessages();
         for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

//        NodeConnectUtil nodeConnectUtil = new NodeConnectUtil();
//        Connection conn = null;
//        Session ssh = null;



//        try {
//            conn = nodeConnectUtil.getConnection(node);
//            ssh = conn.openSession();
//            ssh.execCommand("cat /usr/local/e-srv02-02_20170711_215050.out");
//            InputStream stdout = new StreamGobbler(ssh.getStdout());
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(stdout,"GBK"));
////            while (true)
////            {
////                String line = br.readLine();
////                if (line == null)
////                    break;
////                System.out.println(line);
////            }
//            Map<Integer,String> map = new HashMap<Integer, String>();
//            int linenum = 1;
//            int i = 0;
//            String readLine = null;
//            Log log = new Log();
//            while ((readLine = br.readLine()) != null) {
//                //判断关键字
//
//                if (readLine.indexOf(keyword) != -1) {
//i++;
//                    map.put(linenum, readLine);
//
//                }
//                linenum++;
//            }
//            log.setMessages(map);
//            log.setKeyword(keyword);
//
//            for (Map.Entry<Integer, String> entry : map.entrySet()) {
//                System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
//            }
//System.out.println(i);
//            ssh.close();
//
//            conn.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
////            SCPClient client = new SCPClient(conn);
////            client.get("/usr/local/", localTargetDirectory);
//
//
//   }

    }

}
