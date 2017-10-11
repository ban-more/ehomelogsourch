package com.ehome.sourch.logDao;

import ch.ethz.ssh2.Connection;
import com.ehome.sourch.pojo.Log;
import com.ehome.sourch.pojo.Node;
import com.ehome.sourch.utils.GetIOStream;
import com.ehome.sourch.utils.GetNeedFileUtli;
import com.ehome.sourch.utils.PathUtil;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * 日志伪Dao层
 * Created by wzw on 2017/10/10.
 */
public class LogDaoImpl {

    /**
     *
     * @param node
     * @param nodename
     * @param keyword
     * @return
     */
    public Log findAllLog(Node node, String nodename, String keyword,Connection conn){//在日志文件中查找包含关键字的的简略信息

        PathUtil pathUtil = new PathUtil();
        String path = pathUtil.getPath(nodename);

        GetNeedFileUtli getNeedFileUtli = new GetNeedFileUtli();

        String file = getNeedFileUtli.getNeeedFileName(node,path,conn);

        GetIOStream getIOStream = new GetIOStream();

        Log log = getIOStream.getAllLogs(file, keyword,conn);

        log.setNodename(nodename);


        return log;

    }

    public Log findLogByDate(Node node, String nodename, String keyword,Connection conn){

        PathUtil pathUtil = new PathUtil();
        String path = pathUtil.getPath(nodename);

        GetNeedFileUtli getNeedFileUtli = new GetNeedFileUtli();

        String  file = getNeedFileUtli.getNeeedFileName(node,path,conn);

        GetIOStream getIOStream = new GetIOStream();

        Log log = getIOStream.getLogByDate(file, keyword,conn);

        log.setNodename(nodename);


        return log;
    }

    public Log findLogByDate(Date date1, Node node, String nodename, String keyword, Connection conn) throws ParseException {

        PathUtil pathUtil = new PathUtil();
        String path = pathUtil.getPath(nodename);

        GetNeedFileUtli getNeedFileUtli = new GetNeedFileUtli();

        String  file = getNeedFileUtli.getNeeedFileName(date1,node,path,conn);

        GetIOStream getIOStream = new GetIOStream();

        Log log = getIOStream.getLogByDate(file, keyword,conn);

        log.setNodename(nodename);


        return log;
    }

    public Log findLogByLine(String file, String nodename, int line,Connection conn){


        GetIOStream getIOStream = new GetIOStream();

        Log log = getIOStream.getLogByLine(file, line,conn);

        log.setNodename(nodename);


        return log;
    }
}
