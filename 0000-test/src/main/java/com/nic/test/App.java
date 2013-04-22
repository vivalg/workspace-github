package com.nic.test;

import java.util.Random;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		System.out.println(getRand());
	}

	public static int getRand() {
		Random rand = new Random();
		return Math.abs(rand.nextInt()%100);
	}
}
