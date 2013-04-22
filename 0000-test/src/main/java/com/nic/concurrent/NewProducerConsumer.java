package com.nic.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewProducerConsumer {
	public static void main(String[] args) {
		ExecutorService pool = Executors.newCachedThreadPool();
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
		ProducerThread pt1 = new ProducerThread(queue);
		ProducerThread pt2 = new ProducerThread(queue);
		ProducerThread pt3 = new ProducerThread(queue);
		ConsumerThread ct1 = new ConsumerThread(queue);
		ConsumerThread ct2 = new ConsumerThread(queue);
		ConsumerThread ct3 = new ConsumerThread(queue);
		pt1.setName("生产者1号");
		pt2.setName("生产者2号");
		pt3.setName("生产者3号");
		ct1.setName("消费者1号");
		ct2.setName("消费者1号");
		ct3.setName("消费者1号");
		pool.submit(pt1);
		pool.submit(pt2);
		pool.submit(pt3);
		pool.submit(ct1);
		pool.submit(ct2);
		pool.submit(ct3);
		pool.shutdown();
	}
}

/* 生产者线程 */
class ProducerThread extends Thread {
	private BlockingQueue<Integer> queue;

	public ProducerThread(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}

	public void run() {
		while (true)
			try {
				queue.put(1);
				System.out.println(Thread.currentThread().getName() + "生产了1个");
			} catch (InterruptedException e) {
				e.printStackTrace();

			}
	}
}

/* 消费者线程 */
class ConsumerThread extends Thread {
	private BlockingQueue<Integer> queue;

	public ConsumerThread(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}

	public void run() {
		while (true) {
			try {
				queue.take();
				System.out.println(Thread.currentThread().getName() + "消费了1个");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}