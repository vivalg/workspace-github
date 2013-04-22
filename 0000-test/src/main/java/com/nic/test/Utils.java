package com.nic.test;

public class Utils {

    public static void print(Object...objects){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < objects.length; i++) {
            if(i == 0){
                builder.append(objects[i].toString());
            }else{
                builder .append(", " + objects[i].toString());
            }
        }
        System.out.println(builder.toString());
    }
}
