package com.nic.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class FooFactory {

    public static Foo getFoo(Callback callback) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Foo.class);
        Callback[] callbacks = new Callback[] {callback, NoOp.INSTANCE};
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackFilter(new FooCallbackFilter());
        Object proxy = enhancer.create();
        return (Foo) proxy;
    }
}
