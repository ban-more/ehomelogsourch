package com.ehome.sourch.logService;

import com.ehome.sourch.logDao.LogDao;
import com.ehome.sourch.logDao.LogDaoImpl;
import com.ehome.sourch.pojo.Log;
import com.ehome.sourch.pojo.Node;

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

        List<String> nodenames = new ArrayList<String>();
        nodenames.add("Node_A");
        nodenames.add("Node_B");

        for(int i=0; i < nodes.size(); i++){
            if(nodes.get(i) !=null) {
                Log log1 = logDao.findLogByNew(nodes.get(i), nodenames.get(0), keyword);
                Log log2 = logDao.findLogByNew(nodes.get(i), nodenames.get(1), keyword);

                logs.add(log1);
                logs.add(log2);
            }
        }
        return logs;
    }


    public List<Log> findAllLog(List<Node> nodes, String keyword) throws IOException {
        List<Log> logs = new ArrayList<Log>();

        List<String> nodenames = new ArrayList<String>();
        nodenames.add("Node_A");
        nodenames.add("Node_B");

        for(int i=0; i < nodes.size(); i++){
            if(nodes.get(i) !=null) {
                Log log1 = logDao.findAllLog(nodes.get(i), nodenames.get(0), keyword);
                Log log2 = logDao.findAllLog(nodes.get(i), nodenames.get(1), keyword);

                logs.add(log1);
                logs.add(log2);
            }
        }
        return logs;
    }

    public List<Log> findAllLogByDate(Date date1, List<Node> nodes, String keyword) throws ParseException, IOException {
        List<Log> logs = new ArrayList<Log>();

        List<String> nodenames = new ArrayList<String>();
        nodenames.add("Node_A");
        nodenames.add("Node_B");

        for(int i=0; i < nodes.size(); i++){
            if(nodes.get(i) !=null) {
                Log log1 = logDao.findAllLogByDate(date1,nodes.get(i), nodenames.get(0), keyword);
                Log log2 = logDao.findAllLogByDate(date1,nodes.get(i), nodenames.get(1), keyword);

                logs.add(log1);
                logs.add(log2);
            }
        }
        return logs;
    }

    public List<Log> findLogByNewByDate(Date date1, List<Node> nodes, String keyword) throws ParseException, IOException {
        List<Log> logs = new ArrayList<Log>();

        List<String> nodenames = new ArrayList<String>();
        nodenames.add("Node_A");
        nodenames.add("Node_B");

        for(int i=0; i < nodes.size(); i++){
            if(nodes.get(i) !=null) {
                Log log1 = logDao.findLogByNewByDate(date1,nodes.get(i), nodenames.get(0), keyword);
                Log log2 = logDao.findLogByNewByDate(date1,nodes.get(i), nodenames.get(1), keyword);

                logs.add(log1);
                logs.add(log2);
            }
        }
        return logs;
    }


    public List<Log> findLogByNewByDate(Date date1, Date date2, List<Node> nodes, String keyword) throws ParseException, IOException {
        List<Log> logs = new ArrayList<Log>();

        List<String> nodenames = new ArrayList<String>();
        nodenames.add("Node_A");
        nodenames.add("Node_B");

        for(int i=0; i < nodes.size(); i++){
            if(nodes.get(i) !=null) {
                List<Log> log1 = logDao.findLogByNewByDate(date1,date2,nodes.get(i), nodenames.get(0), keyword);
                List<Log> log2 = logDao.findLogByNewByDate(date1,date2,nodes.get(i), nodenames.get(1), keyword);


                logs.addAll(log1);
                logs.addAll(log2);
            }
        }
        return logs;
    }

    public Log findLogByLine(Node node, String file, String nodename, int line) throws IOException {

        Log log = logDao.findLogByLine(node,file,nodename,line);
        return log;

    }


}
