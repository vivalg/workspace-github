package com.nic.test;

import java.util.Properties;

public class Test {

    public static void main(String[] args) {
    	Properties properties = new Properties();
    	properties.put("ab", "da date");
    	System.out.println(properties.size());
    	System.out.println(properties.get("ab"));
        System.out.println("hello there");
    }

}
