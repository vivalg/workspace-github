package com.nic.spring;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = {"/applicationContext.xml"})
public class TestBean {
    
    @Autowired
    @Qualifier("message1")
    private String marker;

    
    @Test
    public void print(){
        System.out.println(this.marker);
        ApplicationContext context =new ClassPathXmlApplicationContext("/applicationContext.xml");
//        String str1 = (String) context.getBean("message1");
//        System.out.println(str1);
    }
}
