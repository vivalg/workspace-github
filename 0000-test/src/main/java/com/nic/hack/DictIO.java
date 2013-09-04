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

public class DictIO {

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

}
