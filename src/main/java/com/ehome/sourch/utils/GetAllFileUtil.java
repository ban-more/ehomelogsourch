package com.ehome.sourch.utils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.ehome.sourch.pojo.Node;

import java.io.*;

/**
 * 获取指定目录下的所有日志文件
 * Created by wzw on 2017/9/26.
 */
public class GetAllFileUtil {

    private Node node;
    private String path;
    public GetAllFileUtil(Node node,String path){
        this.node = node;
        this.path =path;
    }
    private NodeConnectUtil nodeConnectUtil = new NodeConnectUtil();

    public  String[] getFile() {
            Session ssh = null;
        String[] filenames = new String[30];
        //获取链接
        try {
            Connection conn = nodeConnectUtil.getConnection(node);
//            SCPClient client = new SCPClient(conn);
//            client.get("/usr/local/", localTargetDirectory);

            ssh = conn.openSession();
            ssh.execCommand("find " + path + " -name *.out");
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
//                if (array[i].isFile()){
//                    if(array[i].getName().indexOf(".out")!= -1&&array[i].getName().indexOf("e-ser")!= -1){
//                        filenames[o] = array[i].getName();
//                        o++;
//                    }


//                }

            }
            return filenames;

        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获得指定文件对象
 //       File file = new File(path);
        // 获得该文件夹内的所有文件
  //      File[] array = file.listFiles();




//        String[] filenames = new String[0];
//        int o = 0;
//        for (int i = 0; i < array.length; i++) {
//
//            if (array[i].isFile()){
//                if(array[i].getName().indexOf(".out")!= -1){
//                    filenames[o] = array[i].getName();
//                    o++;
//                }
//
//
//            }
//
//        }

        return filenames;
    }
}


