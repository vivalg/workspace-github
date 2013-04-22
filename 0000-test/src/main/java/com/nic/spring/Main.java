package com.nic.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    // 1、Spring自带依赖注入注解
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        BeanInterface testBean = ctx.getBean(BeanInterface.class);
        testBean.printMessage();
    }

}
