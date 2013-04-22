package com.nic.memcache;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

public class MemcachedTest {

	public static void main(String[] args) throws IOException {
		InetSocketAddress ia = new InetSocketAddress("127.0.0.1", 11211);
		MemcachedClient client = new MemcachedClient(ia);
		
	}
	
}
