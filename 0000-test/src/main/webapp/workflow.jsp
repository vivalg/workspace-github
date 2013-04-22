<%@page import="java.util.*"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.activiti.engine.runtime.ProcessInstance"%>
<%@page import="com.nic.activiti.springintegrated.service.WorkflowTraceService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>work flow</title>
<style type="text/css">
div.diagram_item{border-bottom: 1px solid black;text-align: center;width: auto; }
</style>
</head>
<body>
	<h1>work flow</h1>
	<%
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		WorkflowTraceService workflowTraceService = (WorkflowTraceService)context.getBean("workflowTraceService");
		List<ProcessInstance> list = workflowTraceService.getAllActiveProcesses();
		for(ProcessInstance processInstance : list){
	%>
	<p>
		<div class="diagram_item">
			<ul>
				<li><%=processInstance.getProcessDefinitionId() %></li>
				<li><%=processInstance.getId() %></li>
			</ul>
			<img alt="work flow" src="DiagramServlet.png?processInstanceId=<%=processInstance.getId()%>"/>
		</div>
	</p>
	<%
		}
	%>
</body>
</html>