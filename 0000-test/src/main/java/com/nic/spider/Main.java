package com.nic.spider;

import java.util.Arrays;
import java.util.List;

public class Main {
	
	public static void main(String[] args){
			
			String urlStr = "http://www.163.com/";
			Spider spider = new Spider(urlStr);
			
//			save(spider);
//			getURL(spider);
			count(spider, "netease");
			
	}

	public static void count(Spider spider, String target){
		String content = spider.getContent();
		List<Integer> list = Analyser.extractWords(content, target);
		System.out.println(target + "	total count:" +list.size());
		System.out.println(Arrays.toString(list.toArray()));
	}

	public static void getURL(Spider spider){
		String content = spider.getContent();
		LinkParser parser = new LinkParser();
		
		long startSecond = System.currentTimeMillis();
		List<String> list = parser.getLinks(content);
		long endSecond = System.currentTimeMillis();
		System.out.println("获取URL用时：" +(endSecond - startSecond)/1000d +"秒	: " + spider.getUrl());
		
		System.out.println("link count:" +list.size());
		
		int index = 0;
		for (String url : list) {
			System.out.println(++index + ":	" +url);
		}
		
	}

	public static void save(Spider spider){
		String content = spider.getContent();
		String urlStr = spider.getUrl().toString();
		String encoding = spider.getEncoding();
		String fileName = Tools.parseDomainFromURL(urlStr);
		
		FileProcessor processor = new FileProcessor();
		processor.saveFile(fileName + ".html", encoding, content);
	}

}
