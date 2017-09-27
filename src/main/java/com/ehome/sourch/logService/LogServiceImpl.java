package com.ehome.sourch.logService;

import com.ehome.sourch.pojo.Log;

import java.io.*;
import java.net.InetAddress;
import java.util.*;

/**
 * Created by wzw on 2017/9/26.
 */
public class LogServiceImpl {

    private Log log;

    public Log findLog(String keyword, File file) throws IOException {//在日志文件中查找包含关键字的的简略信息


        log = new Log();
      
        Map<Integer,String> map = null;
        
        LineNumberReader lineReader = new LineNumberReader(new FileReader(file));
        String readLine = null;
        while ((readLine = lineReader.readLine()) != null) {
            //判断关键字
            if (readLine.indexOf(keyword) != -1) {
                
                map.put(lineReader.getLineNumber(),readLine);
            }

        }
        log.setKeyword(keyword);
        log.setMessages(map);
        log.setNodename((InetAddress.getLocalHost()).getHostName());

        return log;
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
