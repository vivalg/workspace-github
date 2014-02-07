package com.nic.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    private static ReentrantLock lock = new ReentrantLock();
    private static Integer i = 0;

    public static void main(String[] args) {

        for (int j = 0; j < 15; j++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        sub();
                        System.out.println(i);
                        try {
                            Thread.sleep(700);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        for (int j = 0; j < 15; j++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        add();
                        System.out.println(i);
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

    }

    public static void add() {
        try {
            lock.lock();
            if (i < 10) {
                i += 1;
            }
        } finally {
            lock.unlock();
        }
    }

    public static void sub() {
        try {
            lock.lock();
            if (i > 0) {
                i += -1;
            }
        } finally {
            lock.unlock();
        }
    }
}
