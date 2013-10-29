package com.nic.test;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

public class Test {

    public static void main(String[] args) {
        Properties properties = System.getProperties();
        Iterator<Entry<Object, Object>> iterator = properties.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Object, Object> entry = iterator.next();
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
