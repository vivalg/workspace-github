package com.nic.multithreading;

import java.util.ArrayList;
import java.util.List;

public class ProducerCosumer {

    private static List<Integer> bucket = new ArrayList<Integer>();

    public static void main(String[] args) {
        new Thread() {
            int i = 0;

            public void run() {
                while (true) {
                    i++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    put(i);
                }
            }
        }.start();

        new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    take();
                }
            }
        }.start();
    }

    private static void put(int elem) {
        synchronized (bucket) {
            if (bucket.size() < 10) {
                bucket.add(elem);
                bucket.notifyAll();
                System.out.println(Thread.currentThread().getId() + " ++++ " + elem);
            } else {
                try {
                    bucket.wait();
                    System.out.println(Thread.currentThread().getId() + " full");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void take() {
        synchronized (bucket) {
            if (bucket.size() > 0) {
                Integer elem = bucket.remove(0);
                bucket.notifyAll();
                System.out.println(Thread.currentThread().getId() + " ---- " + elem);
            } else {
                try {
                    bucket.wait();
                    System.out.println(Thread.currentThread().getId() + " empty");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
