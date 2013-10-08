package com.nic.cglib;

public class Foo {

    public void update() {
        System.out.println("update " + this.getClass().getName());
    }

    public void query() {
        System.out.println("query " + this.getClass().getName());
    }
}
