package com.ehome.sourch.logService;

import ch.ethz.ssh2.Connection;
import com.ehome.sourch.pojo.Log;
import com.ehome.sourch.pojo.Node;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by wzw on 2017/9/26.
 */
public interface LogService {

    public List<Log> findLogByNew(List<Node> nodes, String keyword) throws IOException;

    public List<Log> findAllLog(List<Node> nodes, String keyword)throws IOException;

    public List<Log> findAllLogByDate(Date date1, List<Node> nodes, String keyword) throws ParseException, IOException;

    public List<Log> findLogByNewByDate(Date date1, List<Node>  modes, String keyword) throws ParseException, IOException;

    public List<Log> findLogByNewByDate(Date date1, Date date2, List<Node> nodes, String keyword) throws ParseException, IOException;

    public Log findLogByLine(Node node,String file, int line)throws IOException;
}