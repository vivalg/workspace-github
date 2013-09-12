package com.nic.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarrierTest {

    public static void main(String[] args) {
        // 所有线程执行完后触发
        Runnable barrierAction = new Runnable() {
            public void run() {
                System.out.println("all task finished");
            }
        };

        final CyclicBarrier barrier = new CyclicBarrier(5, barrierAction);

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(Math.round(Math.random() * 2000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread - " + Thread.currentThread().getId() + " - finished");
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        System.out.println("main thread finish");
    }
}
