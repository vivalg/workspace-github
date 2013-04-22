package com.nic.test;

public class StaticBlockTest {

	public static void main(String[] args) throws Exception {
		TTT.foo();
	}
}

class TTT{
	protected static String mark; 
	
	{
		System.out.println("block");
	}
	
	static{
		System.out.println("static block");
	}
	
	public TTT() {
		System.out.println("constructor");
	}
	
	public static void foo(){
		System.out.println("use static mark: " + mark);
	}
	
}