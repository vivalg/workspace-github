package com.nic.activiti.springintegrated.process;

import java.util.HashMap;
import java.util.List;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nic.activiti.springintegrated.AbsProcess;
import com.nic.activiti.springintegrated.service.WorkflowTaskService;

@ContextConfiguration(locations="classpath:activiti-spring.cfg.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SimpleProcessTest extends AbsProcess{
    
    @Autowired
    private WorkflowTaskService workflowTaskService;

    @Override
    protected String getProcessDefinitionKey() {
        return "simpleProcess";
    }
    
    @Test
    public void startProcess(){
        this.startNewProcess("kermit");
    }
    
    @Test
    public void listActiveTask(){
        List<Task> tasks = this.taskService.createTaskQuery().processDefinitionKey(this.getProcessDefinitionKey()).list();
        for (Task task : tasks) {
            System.out.println(">>" + task.getId() + ", " + task.getName());
        }
    }
    
    @Test
    public void stepForward(){
        List<Task> tasks = this.taskService.createTaskQuery().processDefinitionKey(this.getProcessDefinitionKey()).list();
        for (Task task : tasks) {
            this.workflowTaskService.claimUserTask(task.getId(), "kermit");
            this.workflowTaskService.completeUserTask(task.getId(), new HashMap<String, Object>());
        }
    }
    
    @Test
    public void deleteTask(){
        this.taskService.deleteTask("19702");
    }
    
    @Test
    public void reActiviProcess(){
        List<ProcessInstance> processInstances = this.runtimeService.createProcessInstanceQuery().processDefinitionKey(this.getProcessDefinitionKey()).list();
        for (ProcessInstance processInstance : processInstances) {
            System.out.println(processInstance.getId());
            System.out.println(processInstance.isSuspended());
            this.runtimeService.activateProcessInstanceById(processInstance.getId());
            this.taskService.newTask();
        }
    }
    
    
}
