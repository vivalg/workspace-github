package com.nic.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemophoreTest {

    private static final List<Integer> list = new ArrayList<Integer>();

    private static final Semaphore sem = new Semaphore(5);

    public static void main(String[] args) {
        new Thread() {
            public void run() {
                Integer i = 0;
                while (true) {
                    try {
                        Thread.sleep(700);
                        add(i++);
                    } catch (InterruptedException e) {
                        Thread.yield();
                    }
                }
            }
        }.start();
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(900);
                        remove();
                    } catch (InterruptedException e) {
                        Thread.yield();
                    }
                }
            }
        }.start();
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                        System.out.println("====list/permit: " + list.size() + "/" + sem.availablePermits());
                    } catch (InterruptedException e) {
                        Thread.yield();
                    }
                }
            }
        }.start();
    }

    public static boolean add(Integer i) throws InterruptedException {
        sem.acquire();
        boolean addSuccess = false;
        addSuccess = list.add(i);
        if (!addSuccess) {
            sem.release();
        } else {
            System.out.println("++++: " + i);
        }
        return addSuccess;
    }

    public static void remove() throws InterruptedException {
        if (list.size() > 0) {
            sem.release();
            Integer remove = list.remove(0);
            System.out.println("----: " + remove);

        }
    }
}
