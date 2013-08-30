package com.nic.multithreading;

import java.util.ArrayList;
import java.util.List;

public class MultiThreadingTest {

    public static void main(String[] args) {
        Counter counter = new Counter();
        List<Integer> bucket = new ArrayList<Integer>();

        Thread th1 = new Thread(new Task(counter, bucket));
        Thread th2 = new Thread(new Task(counter, bucket));
        Thread th3 = new Thread(new Task(counter, bucket));
        th1.start();
        th2.start();
        th3.start();

    }

}

class Task implements Runnable {

    private Counter counter;
    private List<Integer> bucket;

    public Task(Counter counter, List<Integer> bucket) {
        this.counter = counter;
        this.bucket = bucket;
    }

    public void run() {
        while (true) {
            int next = this.counter.getNext();
            if (bucket.contains(next)) {
                System.out.println(next + " -   " + Thread.currentThread().getId());
            } else {
                bucket.add(next);
                if (bucket.size() % 10 == 0) {
                    System.out.println("size: " + bucket.size());
                }
            }
        }
    }
}

class Counter {
    private Integer val = 0;

    public int getNext() {
        synchronized (val) {
            val++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return val;
        }
    }
}