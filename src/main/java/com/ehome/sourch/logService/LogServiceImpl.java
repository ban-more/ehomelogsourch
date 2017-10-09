package com.ehome.sourch.logService;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import com.ehome.sourch.pojo.Log;
import com.ehome.sourch.pojo.Node;
import com.ehome.sourch.utils.GetIOStream;

import java.io.*;
import java.net.InetAddress;
import java.util.*;

/**
 * Created by wzw on 2017/9/26.
 */
public class LogServiceImpl {

    private Node node;

    public List<Log> findLog(Node node, String file,String keyword) throws IOException {//在日志文件中查找包含关键字的的简略信息

        GetIOStream getIOStream = new GetIOStream();
        BufferedReader br = getIOStream.getInputStream(node ,file);

        System.out.println("************" + br);

        List<Log> logs = new ArrayList<Log>();
        Map<Integer,String> map = null;

//        LineNumberReader lineReader = new LineNumberReader(new InputStreamReader(stdout));
        int linenum = 1;
        String readLine = null;
        while ((readLine = br.readLine()) != null) {
            //判断关键字
            Log log = new Log();
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@" + readLine);

            if (readLine.indexOf(keyword) != -1) {

                map.put(linenum,readLine);
            }
            log.setMessages(map);
            log.setKeyword(keyword);
            logs.add(log);
            linenum++;
        }


        return logs;
    }
    public List<Log> getDetaliLogMessige(Map messages, String file, String keyword) throws IOException {//获取日志中所有的此类型的详细信息

        List<Log> logs = new ArrayList<Log>();
        Log log = new Log();
        Map logmap = new HashMap();
        LineNumberReader lineReader = new LineNumberReader(new FileReader(file));
        String readLine = null;

        Iterator<Map.Entry<String, String>> it = messages.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());

                int i=(Integer.parseInt(entry.getKey()) - 100);
                lineReader.setLineNumber(i);

                while((readLine = lineReader.readLine()) != null){

                if (i <=(Integer.parseInt(entry.getKey()) + 100)) {
                    logmap.put(lineReader.getLineNumber(), readLine);
                }
                if(i == (Integer.parseInt(entry.getKey()) + 101)){
                        break;
                    }
                i++;
            }
            log.setNodename((InetAddress.getLocalHost()).getHostName());
            log.setKeyword(keyword);
            log.setMessages(logmap);
            logs.add(log);
        }
        return logs;
    }


}
