package com.nic.memcache;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;

import com.nic.tool.TablePrinter;

public class XMemcachedTest {

    public static void main(String[] args) throws Throwable {
        MemcachedClient client = springIntegrated();

        Object obj = client.get("name");
        if (obj == null) {
            TBean bean = new TBean();
            bean.setName("jack");
            client.set("name", 600, bean);
            obj = client.get("name");
        }
        System.out.println(">>>>" + obj + "||" + ((TBean) obj).getName());

        printStats(client);
        System.out.println(">>>>done.");
        System.exit(0);
    }

    public static MemcachedClient springIntegrated() throws Throwable {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-memcached.xml");
        MemcachedClient client = context.getBean(MemcachedClient.class);
        return client;
    }

    public static MemcachedClient programaticConnect() throws Throwable {
        XMemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("192.168.119.129:11211"));
        MemcachedClient client = builder.build();
        return client;
    }

    private static void printStats(MemcachedClient client) throws Throwable {
        Map<InetSocketAddress, Map<String, String>> stats = client.getStats();
        for (Entry<InetSocketAddress, Map<String, String>> entry : stats.entrySet()) {
            System.out.println("----" + entry.getKey() + "----");
            TablePrinter.printMap(entry.getValue());
        }
    }
}
