package com.nic.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutrueTest {

    private static final FutureTask<Integer> future = new FutureTask<Integer>(new Callable<Integer>() {
        public Integer call() throws Exception {
            Thread.sleep(2000);
            return 1;
        };
    });

    public static void main(String[] args) {
        try {
            System.out.println("begin");
            new Thread(future).start();
            Integer result = future.get();
            System.out.println("finish");
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
