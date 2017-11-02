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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
            ssh.execCommand("grep -n '"+keyword+"' "+ file);
            InputStream stdout = new StreamGobbler(ssh.getStdout());

            BufferedReader br = new BufferedReader(new InputStreamReader(stdout,"GBK"));

            Map<Long,String> map = new HashMap<Long, String>();
            String readLine = null;

            while ((readLine = br.readLine()) != null) {
                //判断关键字
                String[] str = readLine.split(":",2);
                map.put(Long.valueOf(str[0]), str[1]);
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
                ssh.execCommand("grep -n '"+keyword+"' "+ file);
                InputStream stdout = new StreamGobbler(ssh.getStdout());

                BufferedReader br = new BufferedReader(new InputStreamReader(stdout,"GBK"));

                Map<Long,String> map = new HashMap<Long, String>();
                Long linenum = Long.valueOf(1);
                Long flag = Long.valueOf(0);
                String readline = null;
                String nearline = null;
                while ((readline = br.readLine()) != null) {
                    //判断行数
                    String[] str = readline.split(":",2);
                        nearline = str[1];
                        flag = Long.valueOf(str[0]);
                    linenum++;
                }
                map.put(flag, nearline);
                log.setFilename(file);
                log.setMessages(map);
                log.setKeyword(keyword);
            } catch (IOException e) {
                e.printStackTrace();
            }

        ssh.close();

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

            Map<Long,String> map = new HashMap<Long, String>();
            Long linenum = Long.valueOf(line);
            Long i = Long.valueOf(0);
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

    public Log getLogByDate(String file, String keyword, Date date, Connection conn){
        Session ssh = null;
        Log log = new Log();
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");//将日期转换成字符串类型
        DateFormat df2 = new SimpleDateFormat("yyyy-M-d");
        String date1 = df1.format(date);
        String date2 = df1.format(date);
        try {

            ssh = conn.openSession();
            ssh.execCommand("grep -n '"+keyword+"' "+file+" | grep '"+date1+"\\|"+date2+"'");
            InputStream stdout = new StreamGobbler(ssh.getStdout());

            BufferedReader br = new BufferedReader(new InputStreamReader(stdout,"GBK"));

            Map<Long,String> map = new HashMap<Long, String>();
            String readLine = null;
            String nearline = null;

            while ((readLine = br.readLine()) != null) {
                //判断关键字
                String[] str = readLine.split(":",2);
                map.put(Long.valueOf(str[0]), str[1]);
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

}
