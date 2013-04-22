package com.nic.activiti.springintegrated.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DiagramServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/png");
        ApplicationContext context = new ClassPathXmlApplicationContext("activiti-spring.cfg.xml");
        WorkflowTraceService workflowTraceService = (WorkflowTraceService) context.getBean("workflowTraceService");
        String processInstanceId = request.getParameter("processInstanceId");
        InputStream in = workflowTraceService.getWorkflowDiagram(processInstanceId);
        OutputStream out = response.getOutputStream();
        int len = 0;
        byte[] buf = new byte[1024];
        while((len = in.read(buf)) > 0){
            out.write(buf, 0, len);
        }
        in.close();
        out.flush();
        out.close();
    }

    
}
