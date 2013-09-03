package com.nic.spring.jdbctemplate;

import java.lang.reflect.Field;

public class Test {

    public static void main(String[] args) {
        new Test().test(new TTT());
    }

    public void test(Object o) {
        Field[] declaredFields = o.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field.getName());
        }
    }

}

class TTT {
    private int count;
    private String alia;
}
