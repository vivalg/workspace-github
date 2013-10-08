package com.nic.cglib;


public class Client {

    public static void main(String[] args) {
        FooMethodInterceptor interceptor = new FooMethodInterceptor("jack");
        Foo jackFoo = FooFactory.getFoo(interceptor);
        jackFoo.update();
        jackFoo.query();

        FooMethodInterceptor tomInterceptor = new FooMethodInterceptor("tom");
        Foo tomFoo = FooFactory.getFoo(tomInterceptor);
        tomFoo.update();
        tomFoo.query();
    }
}
