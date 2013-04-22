package com.nic.test;


public class ExternalProgram {
	public static void main(String[] args) {

//		Desktop desktop = Desktop.getDesktop();
//
//		try {
//			java.net.URI uri = new java.net.URI("http://tv.sohu.com");
//			desktop.browse(uri);
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
		
		
		String command = "\"C:/Program Files (x86)/Internet Explorer/iexplore\" tv.sohu.com";
		System.out.println(command);
		try {
			Process p = Runtime.getRuntime().exec(command);
			Thread.sleep(9000);
			p.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
