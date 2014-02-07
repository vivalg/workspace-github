package com.nic.lang;

/**
 * ThreadLocal 可以看做为在不同的方法见传递隐式参数<br/>
 * ThreadLocal可以保存创建耗时的对象，用于线程内共享，用空间换取时间
 *
 * @author willli
 */
public class ThreadLocalTest {

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long threadId = Thread.currentThread().getId();
                    StaticHolder.threadLocal.set(threadId);
                    System.out.println(threadId + " PUT " + threadId);
                    get();
                    get2();
                }
            }).start();
        }
    }

    static void get() {
        Long l = StaticHolder.threadLocal.get();
        System.out.println(Thread.currentThread().getId() + " GET" + l);
    }

    static void get2() {
        Long l = StaticHolder.threadLocal.get();
        System.out.println(Thread.currentThread().getId() + " GET" + l);
    }
}

class StaticHolder {
    public static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();
}