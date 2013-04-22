package com.nic.test;

import java.lang.reflect.Method;

public class ReflectTool {

	public static void invokeAllMethods(Object object, Class<?> c) {
		Method methods[] = c.getMethods();
		for (Method method : methods) {
			try {
				if (method.getParameterTypes().length == 0) {
					method.invoke(object, new Object[0]);
				}
			} catch (Exception e) {
				System.err.println("-- " + method.getName() + " -- " + e.getClass().getSimpleName());
			}
		}
	}
}
