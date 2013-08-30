package com.nic.concurrent;

public class MultiThreadTest {

    private static AA aa = new AA();

    public static void main(String[] args) {
        new Thread(new Worker()).start();
        new Thread(new Worker()).start();
        new Thread(new Worker()).start();
        new Thread(new Worker()).start();
    }

    static class Worker implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.out.println(aa.getCount() + " - " + Thread.currentThread().getId());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class AA {
        private volatile Integer count = 0;

        public int getCount() {
            synchronized (count) {
                this.count++;
                return this.count;
            }
        }
    }
}
