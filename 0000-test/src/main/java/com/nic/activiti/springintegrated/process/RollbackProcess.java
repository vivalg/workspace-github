package com.nic.activiti.springintegrated.process;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nic.activiti.springintegrated.AbsProcess;
import com.nic.activiti.springintegrated.ProcessHelper;
import com.nic.activiti.springintegrated.service.WorkflowTaskService;

@ContextConfiguration(locations="classpath:activiti-spring.cfg.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RollbackProcess extends AbsProcess{
    
    @Autowired
    private WorkflowTaskService workflowTaskService;

    @Override
    protected String getProcessDefinitionKey() {
        return "rollbackProcess";
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
            this.workflowTaskService.completeUserTask(task.getId(), this.getVariables());
        }
    }
    
    private Map<String, Object> getVariables(){
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("agree", "disagree");
        return variables;
    }
    
    @Test
    public void delete(){
        ProcessHelper.deleteProcessCascade(this.getProcessDefinitionKey());
    }
    
    
}
