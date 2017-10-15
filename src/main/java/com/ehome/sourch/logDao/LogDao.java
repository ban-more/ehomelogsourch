package com.ehome.sourch.logDao;
import ch.ethz.ssh2.Connection;
import com.ehome.sourch.pojo.Log;
import com.ehome.sourch.pojo.Node;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by wzw on 2017/10/10.
 */
public interface LogDao {
    public Log findAllLog(Node node, String nodename, String keyword)throws IOException;

    public Log findLogByNew(Node node, String nodename, String keyword)throws IOException;

    public Log findAllLogByDate(Date date1, Node node, String nodename, String keyword) throws ParseException, IOException;

    public Log findLogByNewByDate(Date date1, Node node, String nodename, String keyword) throws ParseException, IOException;

    public List<Log> findLogByNewByDate(Date date1, Date date2, Node node, String nodename, String keyword) throws ParseException, IOException;

    public Log findLogByLine(Node node,String file, String nodename, int line)throws IOException;
    }
