package com.nic.concurrent;

public class DeadLockTest {
    private static final String str1 = "1";
    private static final String str2 = "2";

    public static void main(String[] args) {

        new Thread() {
            public void run() {
                synchronized (str1) {
                    System.out.println(str1);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (str2) {
                        System.out.println(str2);
                    }
                }
                System.out.println("thread 1 finish");
            }
        }.start();

        new Thread() {
            public void run() {
                synchronized (str2) {
                    System.out.println(str2);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (str1) {
                        System.out.println(str1);
                    }
                }
                System.out.println("thread 2 finish");
            }
        }.start();

    }
}
