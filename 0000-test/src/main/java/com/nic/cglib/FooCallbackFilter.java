package com.nic.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.CallbackFilter;

public class FooCallbackFilter implements CallbackFilter {

    private static int AUTH_NEEDED = 0;
    private static int AUTH_NOT_NEEDED = 1;

    @Override
    public int accept(Method method) {
        if (method.getName().equals("update")) {
            return AUTH_NEEDED; // 只对update方法插入验证
        }
        return AUTH_NOT_NEEDED;
    }
}
