package com.ehome.sourch.utils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GetFileLineUtil {

    public String getFileLine(String filename,Connection conn){
        Session ssh = null;
        String line = null;
        try {
            ssh = conn.openSession();
            ssh.execCommand("wc -l " + filename);
            InputStream stdout = new StreamGobbler(ssh.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            String st = br.readLine();
            line = st.split(" ")[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(line);
        return line;
    }
}
