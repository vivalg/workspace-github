package com.nic.test;

import java.io.InputStream;
import java.lang.management.ManagementFactory;


public class JMapTest {

    public static void main(String[] args) throws Exception {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.substring(0, name.indexOf("@"));
        System.out.println(name+", "+pid);
        String path = "D:/Program/Java/jdk1.6.0_33/bin/jmap.exe";
        Process process = new ProcessBuilder(path, "-histo", pid).start();
        InputStream inputStream = process.getInputStream();
        StringBuilder builder = new StringBuilder();
        
        int len = 0;
        byte[] buf = new byte[1024];
        while((len=inputStream.read(buf)) > 0){
            builder.append(new String(buf, 0, len));
        }
        System.out.println(builder.toString());
    }

}
