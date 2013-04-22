package com.nic.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Bean implements BeanInterface{

    @Autowired
    @Qualifier("message2")
	private String msg;
	
	public void printMessage() {
		System.out.println(this.msg);
	}
	
	
}
