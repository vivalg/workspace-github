package com.nic.lang;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynaProxyTest {

    public static void main(String[] args) {
        AProxy aProxy = new AProxy();
        IA ia = (IA) aProxy.bind(new A());
        ia.foo();
    }
}

interface IA {
    public void foo();
}

class A implements IA {
    public void foo() {
        System.out.println("foo: " + this.getClass().getName());
    }
}

class AProxy implements InvocationHandler {
    private Object target;

    public Object bind(Object target) {
        this.target = target;
        Object proxy =  Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
        System.out.println("created proxy: " + proxy.getClass().getCanonicalName());
        return proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("---->start transaction");
        method.invoke(target, args);
        System.out.println("---->end   transaction");
        return null;
    }
}


