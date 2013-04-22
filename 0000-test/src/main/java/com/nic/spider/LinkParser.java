package com.nic.spider;

import java.util.ArrayList;
import java.util.List;

public class LinkParser {

	public List<String> getLinks(String content){
		String linkElem = "<a ";
		String quot = "\"";
		String href = "href=" + quot;
		
		int mark = 0;
		List<String> list = new ArrayList<String>();
		while(true){
			int elemPos = content.indexOf(linkElem, mark);
			
			if(elemPos > 0){
				int hrefStartPos = content.indexOf(href, elemPos + linkElem.length());
				if(hrefStartPos < 0){
					mark = elemPos + linkElem.length();
					continue;
				}
				int hrefEndPos = content.indexOf(quot, hrefStartPos + href.length());
				String url = content.substring(hrefStartPos + href.length(), hrefEndPos);
				list.add(url);
				if(hrefEndPos > 0){
					mark = elemPos + linkElem.length();
					continue;
				}
				mark = hrefEndPos;
			}else{
				break;
			}
		}
		
		return list;
	}

}
