package com.nic.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StaticBean {

    public static String marker;
    
    @Autowired
    public static void setMarker(String marker){
        StaticBean.marker = marker;
    }
    
    public static void print(){
        System.out.println(marker);
    }
}
