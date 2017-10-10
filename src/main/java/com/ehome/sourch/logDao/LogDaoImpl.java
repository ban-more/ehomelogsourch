package com.ehome.sourch.logDao;

import com.ehome.sourch.pojo.Log;
import com.ehome.sourch.pojo.Node;
import com.ehome.sourch.utils.GetIOStream;
import com.ehome.sourch.utils.GetNeedFileUtli;
import com.ehome.sourch.utils.PathUtil;

import java.io.IOException;

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
    public Log findAllLog(Node node, String nodename, String keyword){//在日志文件中查找包含关键字的的简略信息

        Log log= new Log();

        PathUtil pathUtil = new PathUtil();
        String path = pathUtil.getPath(nodename);

        String file = null;
        GetNeedFileUtli getNeedFileUtli = new GetNeedFileUtli();

        file = path + getNeedFileUtli.getNeeedFileName(node,path);

        GetIOStream getIOStream = new GetIOStream();

        log = getIOStream.getAllLogs(node, file, keyword);

        log.setNodename(nodename);


        return log;

    }

    public Log findLogByDate(Node node, String nodename, String keyword){

        Log log= new Log();

        PathUtil pathUtil = new PathUtil();
        String path = pathUtil.getPath(nodename);

        String file = null;
        GetNeedFileUtli getNeedFileUtli = new GetNeedFileUtli();

        file = path + getNeedFileUtli.getNeeedFileName(node,path);

        GetIOStream getIOStream = new GetIOStream();

        log = getIOStream.getLogByDate(node, file, keyword);

        log.setNodename(nodename);


        return log;
    }

    public Log findLogByLine(Node node, String nodename, int line){

        Log log= new Log();

        PathUtil pathUtil = new PathUtil();
        String path = pathUtil.getPath(nodename);

        String file = null;
        GetNeedFileUtli getNeedFileUtli = new GetNeedFileUtli();

        file = path + getNeedFileUtli.getNeeedFileName(node,path);

        GetIOStream getIOStream = new GetIOStream();

        log = getIOStream.getLogByLine(node, file, line);

        log.setNodename(nodename);


        return log;
    }
}
