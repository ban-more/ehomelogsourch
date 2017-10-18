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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 日志伪Dao层
 * Created by wzw on 2017/10/10.
 */
public class LogDaoImpl implements LogDao{

    /**
     * 查找最新生成的日志文件并且取出所有的日志信息
     * @param node
     * @param keyword
     * @return
     */
    public Log findAllLog(Node node,String nodename,String path, String keyword,Connection conn) throws IOException {

        GetNeedFileUtil getNeedFileUtil = new GetNeedFileUtil();

        String file = getNeedFileUtil.getNeeedFileName(node,path,conn);

        FindLogUtil findLogUtil = new FindLogUtil();

        Log log = findLogUtil.getAllLogs(file, keyword,conn);

        log.setNode(node);
        log.setNodename(nodename);
        return log;

    }

    /**
     * 测试最新生成的日志文件并且取出最新的一行日志
     * @param node
     * @param keyword
     * @return
     */
    public Log findLogByNew(Node node,String nodename,String path, String keyword,Connection conn) throws IOException {


        GetNeedFileUtil getNeedFileUtil = new GetNeedFileUtil();

        String  file = getNeedFileUtil.getNeeedFileName(node,path,conn);

        FindLogUtil findLogUtil = new FindLogUtil();

        Log log = findLogUtil.getLogByNew(file, keyword,conn);

        log.setNode(node);
        log.setNodename(nodename);
        return log;
    }

    /**
     * 按照指定日期查找的日志文件并取出所有的日志信息
     * @param date1
     * @param node
     * @param keyword
     * @return
     * @throws ParseException
     */
    public Log findAllLogByDate(Date date1, Node node,String nodename,String path, String keyword,Connection conn) throws ParseException, IOException {


        GetNeedFileUtil getNeedFileUtil = new GetNeedFileUtil();

        String  file = getNeedFileUtil.getNeeedFileName(date1,node,path,conn);

        FindLogUtil findLogUtil = new FindLogUtil();

        Log log = findLogUtil.getAllLogs(file, keyword,conn);

        log.setNode(node);
        log.setNodename(nodename);
        return log;
    }
    /**
     * 按照指定日期查找的日志文件并取出最新的一行日志
     * @param date1
     * @param node
     * @param keyword
     * @return
     * @throws ParseException
     */
    public Log findLogByNewByDate(Date date1, Node node,String nodename,String path, String keyword,Connection conn) throws ParseException, IOException {


        GetNeedFileUtil getNeedFileUtil = new GetNeedFileUtil();

        String  file = getNeedFileUtil.getNeeedFileName(date1,node,path,conn);

        FindLogUtil findLogUtil = new FindLogUtil();

        Log log = findLogUtil.getLogByNew(file, keyword,conn);

        log.setNode(node);
        log.setNodename(nodename);
        return log;
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
    public List<Log> findLogByNewByDate(Date date1, Date date2, Node node,String nodename,String path, String keyword,Connection conn) throws ParseException, IOException {

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
            }
        }

        return logs;
    }

    public Log findLogByLine(Node node, String nodename, String file, int line) throws IOException {

        NodeConnectUtil nodeConnectUtil = new NodeConnectUtil();
        Connection conn = nodeConnectUtil.getConnection(node);

        FindLogUtil findLogUtil = new FindLogUtil();

        Log log = findLogUtil.getLogByLine(file, line,conn);
        log.setNode(node);
        log.setNodename(nodename);
        conn.close();
        System.out.println("连接已关闭");
        return log;
    }
}
