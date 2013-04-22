package com.nic.spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;


public class Spider {
	
	private HttpURLConnection conn;
	private URL url;
	private String encoding;
	private String content;

	public Spider(String urlStr){
		try {
			this.url = new URL(urlStr);
			this.conn = (HttpURLConnection) url.openConnection();
			this.encoding = this.getPageEncoding(this.conn);
			this.craw();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			conn.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
			conn.disconnect();
		}
	}
	
	public URL getUrl() {
		return url;
	}
	
	public HttpURLConnection getConn() {
		return conn;
	}
	
	public String getEncoding() {
		return encoding;
	}

	public String getContent() {
		return content;
	}
	
	public void craw(){
		long startSecond = System.currentTimeMillis();
		
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = null;
		try {
			InputStreamReader isr = new InputStreamReader(this.conn.getInputStream(), this.encoding);
			reader = new BufferedReader(isr);
			int bufLen = 1024;
			char[] temp = new char[bufLen];
			int len = 0;
			while((len = reader.read(temp, 0, bufLen)) != -1){
				sb.append(new String(temp,0,len));
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			conn.disconnect();
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		this.content =  sb.toString();
		
		long endSecond = System.currentTimeMillis();
		System.out.println("爬行用时：" +(endSecond - startSecond)/1000d +"秒	: " + this.url);
		
	}
	
	public String getPageEncoding(HttpURLConnection conn){
		String encoding = "GBK";
		
		Map<String, List<String>> map = conn.getHeaderFields();
		List<String> list = map.get("Content-Type");
		if(list.size() > 0){
			String paraVal = list.get(0);
			int encodingPos = paraVal.indexOf("charset=");
			if(encodingPos != -1){
				encoding = paraVal.substring(encodingPos + 8, paraVal.length());
			}
		}
		/*
		// debug
		Iterator<Map.Entry<String, List<String>>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, List<String>> entry = it.next();
			String key = entry.getKey();
			List<String> values = entry.getValue();
			
			System.out.println(key);
			for (int i = 0; i < values.size(); i++) {
				System.out.println("	-" + values.get(i));
			}
		}
		// debug end
		*/
		return encoding;
	}
	
}
