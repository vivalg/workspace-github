package com.nic.memcache;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

public class SypMemcachedTest {

    public static void main(String[] args) {
        InetSocketAddress ia = new InetSocketAddress("192.168.119.129", 11211);
        MemcachedClient client = null;
        try {
            client = new MemcachedClient(ia);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        Object object = client.get("name");
        System.out.println(object);
        System.out.println("finish");
    }

}
