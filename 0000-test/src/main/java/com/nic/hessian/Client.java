package com.nic.hessian;

import com.caucho.hessian.client.HessianProxyFactory;

public class Client {

    public static void main(String[] args) throws Exception {
        HessianProxyFactory proxyFactory = new HessianProxyFactory();
        Hello hello = (Hello) proxyFactory.create(Hello.class, "http://localhost/webservice");
        String returnMsg = hello.hello("hahaha");
        System.out.println(returnMsg);
    }
}
