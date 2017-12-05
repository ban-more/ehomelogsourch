package com.ehome.sourch.utils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.ehome.sourch.pojo.Node;

import java.io.*;

/**
 * 获取指定目录下的所有日志文件
 * Created by wzw on 2017/9/26.
 */
public class GetAllFileUtil {

    public  String[] getFile(String path, Connection conn) throws IOException {
            Session ssh = null;
        String[] filenames = new String[30];
        //获取链接
        try {

            ssh = conn.openSession();
            ssh.execCommand("cd /weblogic ; find " + path + " -name *.out","GBK");
            System.out.println("cd /weblogic ; find " + path + " -name *.out");
            InputStream stdout = new StreamGobbler(ssh.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout,"GBK"));
            String[] array = new String[30];
            int j=0;
            System.out.println("ExitCode: " + ssh.getExitStatus());
            while (true)
            {
                String line = br.readLine();
                if (line == null)
                    break;
                array[j] = line;
                j++;
                System.out.println(line);
            }
            int o = 0;
            for (int i = 0; i < array.length; i++) {
                if(array[i] != null) {

                    if (array[i].indexOf("e-srv") != -1) {
                        filenames[o] = array[i];
                        o++;
                    }
                }

            }


            return filenames;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return filenames;
    }
    public String[] getfilename(String path,Connection conn){
        Session ssh = null;
        String[] filenames = new String[30];
        //获取链接
        try {

            ssh = conn.openSession();
            ssh.execCommand("find " + path + " -name *.log");
            InputStream stdout = new StreamGobbler(ssh.getStdout());

            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            String[] array = new String[30];
            int j=0;
            while (true) {

                // attention: do not comment this block, or you will hit
                // NullPointerException

                // when you are trying to read exit status

                String line = br.readLine();

                if (line == null) {
                    break;
                }

                array[j] = line;
                j++;

            }

            int o = 0;
            for (int i = 0; i < array.length; i++) {
                if(array[i] != null) {

                    if (array[i].indexOf("e-srv") != -1) {
                        filenames[o] = array[i];
                        o++;
                    }
                }

            }


            return filenames;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return filenames;
    }

}


