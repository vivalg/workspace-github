<%@page import="java.util.*"%>
<%@page import="com.nic.spider.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
</head>
<body>
	<h1>login2</h1>
	<div>
		<p>welcome to login</p>
		<p>user name: <%=request.getParameter("username") %></p>
	</div>
</body>
</html>