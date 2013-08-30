package com.nic.concurrent;

import java.util.Vector;

public class ProducerConsumer {

    private Vector<Integer> bucket = new Vector<Integer>();
    private static Integer MAX = 5;
    private volatile int i = 0;

    public static void main(String[] args) throws Exception {
        ProducerConsumer inst = new ProducerConsumer();
        boolean isSleep = true;
        new Thread(inst.new Producer(isSleep , 50)).start();
        new Thread(inst.new Producer(isSleep, 50)).start();
        new Thread(inst.new Consumer(isSleep, 50)).start();
        new Thread(inst.new Consumer(isSleep, 50)).start();
    }

    class Producer implements Runnable {

        private int frequency;
        private boolean isSleep;

        public Producer(boolean isSleep, int freq) {
            this.isSleep = isSleep;
            this.frequency = freq;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (bucket) {

                    while (bucket.size() >= MAX) {
                        try {
                            bucket.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    i++;
                    bucket.add(i);
                    System.out.println(i + "    - put:" + Thread.currentThread().getId());
                    bucket.notifyAll();

                    if (isSleep) {
                        try {
                            Thread.sleep(this.frequency);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }

    class Consumer implements Runnable {
        private int frequency;
        private boolean isSleep;

        public Consumer(boolean isSleep, int freq) {
            this.isSleep = isSleep;
            this.frequency = freq;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (bucket) {
                    while (bucket.size() == 0) {
                        try {
                            bucket.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    Integer obj = bucket.remove(0);
                    System.out.println(obj + "  - get:" + Thread.currentThread().getId());
                    bucket.notifyAll();

                    if (isSleep) {
                        try {
                            Thread.sleep(this.frequency);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }

    }
}
