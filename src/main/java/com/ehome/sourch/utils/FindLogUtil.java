package com.ehome.sourch.utils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.ehome.sourch.pojo.Log;
import com.ehome.sourch.pojo.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取日志文件中包含关键字的日志
 * Created by wzw on 2017/10/9.
 */
public class FindLogUtil {

    /**
     * 获取包含关键字的一行信息
     * @param file
     * @param keyword
     * @return
     */
    public Log getAllLogs(String file, String keyword,Connection conn){
        Session ssh = null;
        Log log = new Log();

        try {

            ssh = conn.openSession();
            ssh.execCommand("cat " + file);
            InputStream stdout = new StreamGobbler(ssh.getStdout());

            BufferedReader br = new BufferedReader(new InputStreamReader(stdout,"GBK"));

            Map<Integer,String> map = new HashMap<Integer, String>();
            int linenum = 1;
            int i = 0;
            String readLine = null;

            while ((readLine = br.readLine()) != null) {
                //判断关键字

                if (readLine.indexOf(keyword) != -1) {
                    i++;
                    map.put(linenum, readLine);

                }
                linenum++;
            }
            log.setFilename(file);
            log.setMessages(map);
            log.setKeyword(keyword);


            ssh.close();

                    } catch (IOException e) {
                    e.printStackTrace();
                    }


        return log;
    }

    /**
     * 获取包含关键字的最新的一行信息
     * @param file
     * @param keyword
     * @return
     */
    public Log getLogByNew(String file, String keyword,Connection conn){
        Session ssh = null;
        Log log = new Log();

        try {

            ssh = conn.openSession();
            ssh.execCommand("cat " + file);
            InputStream stdout = new StreamGobbler(ssh.getStdout());

            BufferedReader br = new BufferedReader(new InputStreamReader(stdout,"GBK"));

            Map<Integer,String> map = new HashMap<Integer, String>();
            int linenum = 1;
            int flag = 0;

            String readLine = null;
            String nearline = null;

            while ((readLine = br.readLine()) != null) {
                //判断关键字

                if (readLine.indexOf(keyword) != -1) {
                    nearline = readLine;
                    flag = linenum;
                }
                linenum++;
            }
            map.put(flag, nearline);
            log.setFilename(file);
            log.setMessages(map);
            log.setKeyword(keyword);

            ssh.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return log;
    }

    /**
     * 获取选中的某行的详细信息及上下文
     * @param line
     * @param file
     * @return
     */
    public Log getLogByLine(String file,int line,Connection conn){

        Session ssh = null;
        Log log = new Log();

        try {
            ssh = conn.openSession();
            ssh.execCommand("cat " + file + " | tail -n +" + line +" | head -n 100");
            InputStream stdout = new StreamGobbler(ssh.getStdout());

            BufferedReader br = new BufferedReader(new InputStreamReader(stdout,"GBK"));

            Map<Integer,String> map = new HashMap<Integer, String>();
            int linenum = line;
            int i = 0;
            String readLine = null;

            while ((readLine = br.readLine()) != null) {
                //判断关键字

                    i++;
                    map.put(linenum, readLine);

                linenum++;
            }
            log.setFilename(file);
            log.setMessages(map);
            ssh.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
//            SCPClient client = new SCPClient(conn);
//            client.get("/usr/local/", localTargetDirectory);


        return log;
    }


}
