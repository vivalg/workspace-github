<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>upload</title>
</head>
<body>
	<h1>file upload</h1>
	<div>
		<form action="/file-upload.htm" method="post" enctype="multipart/form-data">
			<label for="user">user: </label>
			<input type="text" id="user" name="user"/><br/>
			
			<label for="age">age: </label>
			<input type="text" id="age" name="age"/><br/>
			
			<label for="file1">file1: </label>
			<input type="file" id="file1" name="file1"/><br/>
			
			<label for="file2">file2: </label>
			<input type="file" id="file2" name="file2"/><br/>
			
			<input type="submit"/>
		</form>
	</div>
</body>
</html>