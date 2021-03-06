package com.ehome.sourch.logDao;

import ch.ethz.ssh2.Connection;
import com.ehome.sourch.pojo.Log;
import com.ehome.sourch.pojo.Node;
import com.ehome.sourch.utils.FindLogUtil;
import com.ehome.sourch.utils.GetNeedFileUtil;
import com.ehome.sourch.utils.NodeConnectUtil;
import com.ehome.sourch.utils.PathUtil;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * 日志伪Dao层
 * Created by wzw on 2017/10/10.
 */
public class LogDaoImpl{

    /**
     * 查找最新生成的日志文件并且取出所有的日志信息
     * @param node
     * @param keyword
     * @return
     */
    public List<Log> findAllLog(Node node, String keyword,Connection conn) throws IOException {

        GetNeedFileUtil getNeedFileUtil = new GetNeedFileUtil();
        String[] filename = getNeedFileUtil.getNeeedFileName(node,conn);

        Log log1 = new Log();
        List<Log> logs = new ArrayList<Log>();
        if(filename == null){
            log1.setNode(node);
            log1.setFilename("This server does not have qualified file!!");
            log1.setMessages(null);
            logs.add(log1);
            return logs;
        }
        for(int i = 0 ; i<filename.length ; i++) {
            if (filename[i] != null) {
                FindLogUtil findLogUtil = new FindLogUtil();
                log1 = findLogUtil.getAllLogs(filename[i], keyword, conn);
                log1.setNode(node);
                if(filename[i].indexOf("-01_") != -1) {
                    log1.setNodename("srv01");
                }else if(filename[i].indexOf("-02_") != -1){
                    log1.setNodename("srv02");
                }
            } else {
                log1.setNode(node);
                if(filename[i].indexOf("-01_") != -1) {
                    log1.setNodename("srv01");
                }else if(filename[i].indexOf("-02_") != -1){
                    log1.setNodename("srv02");
                }
                log1.setFilename("This server does not have qualified file!!");
                log1.setMessages(null);
            }
            logs.add(log1);
        }
        return logs;
    }

    /**
     * 测试最新生成的日志文件并且取出最新的一行日志
     * @param node
     * @param keyword
     * @return
     */
    public List<Log> findLogByNew(Node node, String keyword,Connection conn) throws IOException, InterruptedException {


        GetNeedFileUtil getNeedFileUtil = new GetNeedFileUtil();

        String[] filename = getNeedFileUtil.getNeeedFileName(node,conn);
        FindLogUtil findLogUtil = new FindLogUtil();
        Log log1 = new Log();
        List<Log> logs = new ArrayList<Log>();
        if(filename == null){
            log1.setNode(node);
            log1.setFilename("This server does not have qualified file!!");
            log1.setMessages(null);
            logs.add(log1);
            return logs;
        }
        for(int i = 0 ; i<filename.length ; i++) {
            if (filename[i] != null) {
                System.out.println("开始从文件中取日志！！");
                log1 = findLogUtil.getLogByNew(filename[i], keyword, conn);
                log1.setNode(node);
                if (filename[i].indexOf("-01_") != -1) {
                    log1.setNodename("srv01");
                } else if (filename[i].indexOf("-02_") != -1) {
                    log1.setNodename("srv02");
                }
            } else {
                log1.setNode(node);
                if(filename[i].indexOf("-01_") != -1) {
                    log1.setNodename("srv01");
                }else if(filename[i].indexOf("-02_") != -1){
                    log1.setNodename("srv02");
                }
                log1.setFilename("This server does not have qualified file!!");
                log1.setMessages(null);
            }
            logs.add(log1);
        }
        return logs;
    }

    /**
     * 按照指定日期查找的日志文件并取出所有的日志信息
     * @param date1
     * @param node
     * @param keyword
     * @return
     * @throws ParseException
     */
    public List<Log> findAllLogByDate(Date date1, Node node, String keyword,Connection conn) throws ParseException, IOException {


        GetNeedFileUtil getNeedFileUtil = new GetNeedFileUtil();

        String[] filename = getNeedFileUtil.getNeeedFileName(date1,node,conn);

        Log log1 = new Log();
        List<Log> logs = new ArrayList<Log>();
        if(filename == null){
            log1.setNode(node);
            log1.setFilename("This server does not have qualified file!!");
            log1.setMessages(null);
            logs.add(log1);
            return logs;
        }
        for(int i = 0 ; i<filename.length ; i++) {
            if (filename[i] != null) {
                FindLogUtil findLogUtil = new FindLogUtil();
                log1 = findLogUtil.getLogByDate(filename[i], keyword, date1, conn);
                log1.setNode(node);
                if (filename[i].indexOf("-01_") != -1) {
                    log1.setNodename("srv01");
                } else if (filename[i].indexOf("-02_") != -1) {
                    log1.setNodename("srv02");
                }
            }
//            } else {
//                log1.setNode(node);
//                if(filename[i].indexOf("-01_") != -1) {
//                    log1.setNodename("srv01");
//                }else if(filename[i].indexOf("-02_") != -1){
//                    log1.setNodename("srv02");
//                }
//                log1.setFilename("This server does not have qualified file!!");
//                log1.setMessages(null);
//            }
            logs.add(log1);
        }
        return logs;
    }
    /**
     * 按照指定日期查找的日志文件并取出最新的一行日志
     * @param date1
     * @param node
     * @param keyword
     * @return
     * @throws ParseException
     */
    public List<Log> findLogByNewByDate(Date date1, Node node, String keyword,Connection conn) throws ParseException, IOException, InterruptedException {

        GetNeedFileUtil getNeedFileUtil = new GetNeedFileUtil();

        String[] filename = getNeedFileUtil.getNeeedFileName(date1,node,conn);

        Log log1 = new Log();
        List<Log> logs = new ArrayList<Log>();
        if(filename == null){
            log1.setNode(node);
            log1.setFilename("This server does not have qualified file!!");
            log1.setMessages(null);
            logs.add(log1);
            return logs;
        }
        for(int i = 0 ; i<filename.length ; i++) {
            if (filename[i] != null) {
                FindLogUtil findLogUtil = new FindLogUtil();
                log1 = findLogUtil.getLogByNew(filename[i], keyword, conn);
                log1.setNode(node);
                if (filename[i].indexOf("-01_") != -1) {
                    log1.setNodename("srv01");
                } else if (filename[i].indexOf("-02_") != -1) {
                    log1.setNodename("srv02");
                }
            }
//            } else {
//                log1.setNode(node);
//                if(filename[i].indexOf("-01_") != -1) {
//                    log1.setNodename("srv01");
//                }else if(filename[i].indexOf("-02_") != -1){
//                    log1.setNodename("srv02");
//                }
//                log1.setFilename("This server does not have qualified file!!");
//                log1.setMessages(null);
//            }
            logs.add(log1);
        }
        return logs;
    }

    /**
     * 按照指定日期内查找的所有日志文件并取出最新的一行日志
     * @param date1
     * @param date2
     * @param node
     * @param keyword
     * @return
     * @throws ParseException
     */
    public List<Log> findLogByNewByDate(Date date1, Date date2, Node node,String nodename,String path, String keyword,Connection conn) throws ParseException, IOException, InterruptedException {

        List<Log> logs = new ArrayList<Log>();

        GetNeedFileUtil getNeedFileUtil = new GetNeedFileUtil();

        String[]  files = getNeedFileUtil.getNeeedFileName(date1,date2,node,path,conn);

        FindLogUtil findLogUtil = new FindLogUtil();
        for(int i =0; i < files.length; i++) {
            if(files[i]!=null) {
                Log log = findLogUtil.getLogByNew(files[i], keyword, conn);
                log.setNode(node);
                log.setNodename(nodename);
                logs.add(log);
                System.out.println(files[i]);
            }else{
                Log log = new Log();
                log.setNode(node);
                log.setNodename(nodename);
                log.setFilename("This server does not have qualified file!!");
                log.setMessages(null);
                logs.add(log);
            }
        }

        return logs;
    }

    public Log findLogByLine(Node node, String nodename,String keyword, String file, Long line,Connection conn) throws IOException {


        Log log = new Log();
        if (file!=null) {
            FindLogUtil findLogUtil = new FindLogUtil();
            log = findLogUtil.getLogByLine(file,line,conn);

            log.setKeyword(keyword);
            log.setNode(node);
            log.setNodename(nodename);
        }else{

            log.setNode(node);
            log.setNodename(nodename);
            log.setFilename("This server does not have qualified file!!");
            log.setMessages(null);
        }
        return log;
    }
}
