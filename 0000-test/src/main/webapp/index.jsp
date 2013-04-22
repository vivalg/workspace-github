<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
</head>
<body>
	<h1>index</h1>
	<div>
		<ul>
			<li><a href="/index.htm?para1=ppp&para2=ccc&para3=dddd">page1 (with parameters)</a></li>
			<li><a href="/index.htm">page1</a></li>
			<li><a href="/login.htm">login</a></li>
			<li>
				<form action="/login2.htm" method="post">
					<input type="text" name="username"  value="jack" readonly="readonly" style="background-color: #DDD;"/>
					<input type="submit" value="post">
				</form>
			</li>
			<li><a href="/jstl.jsp">jstl</a></li>
			<li><a href="/file-upload.jsp">file upload</a></li>
			<li><a href="/workflow.jsp">work flow</a></li>
		</ul>
	</div>
</body>
</html>