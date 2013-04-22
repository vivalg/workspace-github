package com.nic.spider;


public class Tools {

	public static String parseDomainFromURL(String urlStr){
		int protocolPos = urlStr.indexOf("://") + 3;
		int domainEndPos = urlStr.indexOf("/", protocolPos);
		String domain = urlStr.substring(protocolPos, domainEndPos);
		return domain;
	}

}
