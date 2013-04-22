package com.nic.test;

import java.lang.reflect.Field;

public class ReflectPrivateFieldTest {

	public static void main(String[] args) throws Throwable {
	    Class<?> c = Class.forName("com.nic.test.TT");
	    Object o = c.newInstance();
	    Field[] fields = c.getDeclaredFields();
	    for (Field field : fields) {
	        System.out.println(field.getName());
	        if(!field.isAccessible()){
	            System.out.println(field.isAccessible());
	            field.setAccessible(true);
	            System.out.println(field.isAccessible());
	        }
            field.set(o, "funk");
        }
	    
	    ((TT)o).print();
	    
	}
}

class TT{
    private String mark;
    public void print(){
        System.out.println(mark);
    }
}

