<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String marker = "hi there";
	pageContext.setAttribute("marker", marker);
	pageContext.setAttribute("rq", request);
	String name = "jack";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
<style type="text/css">
body{white-space-collapse: discard;}
</style>
</head>
<body>
	<h1>JSTL</h1>
	<div>
		<ul>
			<li><%="hello" %></li>
			<li>${marker}</li>
			<li>${rq.getParameterMap()}</li>
			<li><c:out value="${marker }"></c:out></li>
		</ul>
	</div>
</body>
</html>