package com.nic.poi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class ExcelReader {

	public static void main(String[] args) throws Throwable {
		long start = System.currentTimeMillis();
		File file = new File("/Download/2000w/600W-800W.csv");
		// File file = new File("/Download/2000w/last5000.csv");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		int index = 0;
		Map<String, String> caches = new HashMap<String, String>();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		while ((line = reader.readLine()) != null) {
			index++;
			String[] split = line.split(",");
			if (split != null) {
				Integer len = split.length;
				if (map.containsKey(len)) {
					map.put(len, map.get(len) + 1);
				} else {
					map.put(len, 1);
				}
				if (len != 33) {
					caches.put(index + "[" + len + "]", line);
				}
			} else {
				if (map.containsKey(null)) {
					map.put(null, map.get(null) + 1);
				} else {
					map.put(null, 1);
				}
			}
		}

		System.out.println("---------");
		long end = System.currentTimeMillis();
		System.out.println("time used: " + (end - start) / 1000);
		System.out.println("=========");
		Iterator<Entry<Integer, Integer>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, Integer> elem = iterator.next();
			System.out.println(elem.getKey() + " == " + elem.getValue());
		}
		System.out.println("++++++++++");
		Iterator<Entry<String, String>> iterator2 = caches.entrySet().iterator();
		while (iterator2.hasNext()) {
			Entry<String, String> entry = iterator2.next();
			System.out.println(entry.getKey() + " >> " + entry.getValue());
		}
	}
}
