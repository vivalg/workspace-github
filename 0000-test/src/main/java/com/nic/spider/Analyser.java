package com.nic.spider;

import java.util.ArrayList;
import java.util.List;

public class Analyser {

	public static List<Integer> extractWords(String content, String target){
		long startSecond = System.currentTimeMillis();
		
		int mark = 0;
		List<Integer> list = new ArrayList<Integer>();
		while(true){
			int pos = content.indexOf(target, mark + target.length());
			if(pos != -1){
				list.add(pos);
				mark = pos;
			}else{
				break;
			}
		}
		
		long endSecond = System.currentTimeMillis();
		System.out.println("计数用时：" +(endSecond - startSecond)/1000d +"秒	: " + target);
		
		return list;
	}

}
