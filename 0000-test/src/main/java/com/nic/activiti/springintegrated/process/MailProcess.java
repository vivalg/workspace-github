package com.nic.activiti.springintegrated.process;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.junit.Test;

import com.nic.activiti.springintegrated.ProcessHelper;


public class MailProcess {

    static String processDefinitionKey = "simpleMail";
    
    @Test
    public void startInstance(){
        ProcessHelper.startNewProcessInstance("kermit", "simpleMail");
    }
    
    @Test
    public void stepForward(){
        String userId = "kermit";
        Map<String, Object> variables = new HashMap<String, Object>();
        setMailPara(variables);
        List<Task> tasks = ProcessHelper.taskService.createTaskQuery().processDefinitionKey(processDefinitionKey).list();
        for(Task task : tasks){
            ProcessHelper.taskService.claim(task.getId(), userId);
            ProcessHelper.taskService.complete(task.getId(), variables);
        }
        
    }
    
    public void setMailPara(Map<String, Object> variables){
        variables.put("sender", "vivalg@sina.com");
        variables.put("recipient", "vivalg@sina.com");
        variables.put("male", true);
        variables.put("orderId", "12399");
        variables.put("recipientName", "Lock Smith");
        variables.put("now", new Date());
    }
}
