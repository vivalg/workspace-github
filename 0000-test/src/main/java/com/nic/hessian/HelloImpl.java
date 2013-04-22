package com.nic.hessian;

public class HelloImpl implements Hello{

    @Override
    public String hello(String msg) {
        Bean bean = new Bean();
        bean.doPrint(msg);
        return "return:" + msg;
    }

}
