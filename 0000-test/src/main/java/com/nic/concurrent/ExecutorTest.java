package com.nic.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorTest {

    public static void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newFixedThreadPool(3);
        Runnable worker = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId());
                try {
                    Thread.sleep(900);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        while (true) {
            executor.execute(worker);
            Thread.sleep(1000);
        }

    }
}
