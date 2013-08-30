package com.nic.multithreading;

public class PublishTest {

    private Integer value = 0;

    public static void main(String[] args) {
        final PublishTest inst = new PublishTest();
        new Thread() {
            public void run() {
                while (inst.value < 10) {
                    System.out.println("not yet:" + inst.value);
                    Thread.yield();
                }
                System.out.println("wait finish");
            }
        }.start();
        new Thread() {
            public void run() {
                for (int i = 0; i <= 10; i++) {
                    inst.value++;
                    System.out.println("----" + inst.value);
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("increase finish");
            }
        }.start();
    }

}
