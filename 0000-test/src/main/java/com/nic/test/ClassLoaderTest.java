package com.nic.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

public class ClassLoaderTest {

    public static void main(String[] args) throws InterruptedException, IOException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        String name = contextClassLoader.getClass().getName();
        System.out.println(name);

        URL resource = contextClassLoader.getResource("log4j.properties");
        String file = resource.getFile();
        System.out.println(file);

        Properties properties = new Properties();
        properties.load(new FileInputStream(new File(file)));
        Iterator<Entry<Object, Object>> iterator = properties.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Object, Object> en = iterator.next();
            System.out.println(en.getKey() + " -> " + en.getValue());
        }

    }

}