package com.nic.hack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

public class DictIO {

    private static final ArrayBlockingQueue<String> cacheQueue = new ArrayBlockingQueue<String>(2000);
    private static BufferedWriter writer = null;

    static {
        File historyFile = new File("/data/dict/his.txt");
        try {
            if (!historyFile.exists()) {
                historyFile.createNewFile();
            }
            writer = new BufferedWriter(new FileWriter(historyFile, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(Set<String> pwds, File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (String pwd : pwds) {
            writer.write(pwd);
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }

    public static Set<String> readFile(File file) throws IOException {
        Set<String> set = new HashSet<String>();
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        while ((line = reader.readLine()) != null) {
            set.add(line);
        }
        reader.close();
        return set;
    }

    public static Set<String> readSixDigitFromAllDicts() throws IOException {
        Set<String> set = new HashSet<String>();
        File dir = new File("./src/main/resources/dict");
        File[] files = dir.listFiles();

        String line;
        for (File file : files) {
            if (file.isDirectory()) {
                continue;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while ((line = reader.readLine()) != null) {
                if (line.length() == 6) {
                    try {
                        Integer.parseInt(line);
                        set.add(line);
                    } catch (Exception e) {
                        // do nothing
                    }
                }
            }
        }
        return set;
    }

    public static Set<String> readFileWithHistryRemoved(File file, File hisFile) throws IOException {
        Set<String> wholeSet = readFile(file);
        System.out.println("--->" + wholeSet.size());
        Set<String> hisSet = readFile(hisFile);
        System.out.println("--->" + hisSet.size());
        for (String elem : hisSet) {
            wholeSet.remove(elem);
        }
        System.out.println("--->" + wholeSet.size());
        return wholeSet;
    }

    public static void writeHistory(String str) throws IOException {
        synchronized (cacheQueue) {
            cacheQueue.add(str);
            if (cacheQueue.size() >= 100) {
                String elem = null;
                while ((elem = cacheQueue.poll()) != null) {
                    writer.write(elem);
                    writer.newLine();
                }
                writer.flush();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Set<String> set = DictGen.genSixDigit();
        DictIO.writeFile(set, new File("/data/dict/6digit.txt"));
    }

}
