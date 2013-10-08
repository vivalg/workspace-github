package com.nic.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class FooMethodInterceptor implements MethodInterceptor {

    private String userName;

    public FooMethodInterceptor(String userName) {
        this.userName = userName;
    }

    @Override
    public Object intercept(Object object, Method method, Object[] arguments, MethodProxy methodProxy) throws Throwable {
        if (!"jack".equals(this.userName)) {
            System.out.println("###not jack, no privilege!");
            return null;
        }
        return methodProxy.invokeSuper(object, arguments);
    }

}
