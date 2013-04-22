<%@page import="java.util.*"%>
<%@page import="com.nic.spider.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String urlStr = "http://www.163.com/";
	Spider spider = new Spider(urlStr);
	
	String content = spider.getContent();
	String target = "163";
	List<Integer> list = Analyser.extractWords(content, target);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
</head>
<body>
	<h1>spider</h1>
	<div>
		<p>total occur: <%=list.size()	%></p>
		<p>
		<%
			for(int i = 0; i< list.size(); i++){
				out.print(list.get(i)+"&nbsp;");
				if((i+1)%20 == 0){
					out.println("<br/>");
				}
			}
		%>
		</p>
	</div>
</body>
</html>