package com.nic.activiti.springintegrated.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.nic.activiti.springintegrated.ProcessHelper;

@ContextConfiguration(locations="classpath:activiti-spring.cfg.xml")
public class Main{

    @Autowired
    WorkflowProcessDefinitionService workflowProcessDefinitionService;
    WorkflowTraceService workflowTraceService;
    
    public Main() {
        ApplicationContext context = new ClassPathXmlApplicationContext("activiti-spring.cfg.xml");
        this.workflowProcessDefinitionService = (WorkflowProcessDefinitionService) context.getBean("workflowProcessDefinitionService");
        this.workflowTraceService = (WorkflowTraceService) context.getBean("workflowTraceService");
    }
    
    @Test
    public void deploy(){
        try {
            this.workflowProcessDefinitionService.deployFromClasspath("simpleProcess", "gateway01");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void deployZip(){
        try {
            this.workflowProcessDefinitionService.deploySingleZipProcess("SimpleMail");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void deleteCascade(){
        this.workflowProcessDefinitionService.cleanProcessDefinition("simpleMail");
    }
    
    @Test
    public void testDiagram() throws IOException{
            List<ProcessInstance> processInstances = ProcessHelper.runtimeService.createProcessInstanceQuery().list();
            for (ProcessInstance processInstance : processInstances) {
                InputStream is = workflowTraceService.getWorkflowDiagram(processInstance.getId());
                FileOutputStream out = new FileOutputStream("d:/download/" + processInstance.getId() + ".png");
                int read = 0;
                byte[] bytes = new byte[1024];
                while((read = is.read(bytes)) > 0){
                    out.write(bytes, 0, read);
                }
                is.close();
                out.flush();
                out.close();
            }
    }
}
