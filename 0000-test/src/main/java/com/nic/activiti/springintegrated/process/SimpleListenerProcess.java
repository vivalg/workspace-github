package com.nic.activiti.springintegrated.process;

import java.util.List;

import org.activiti.engine.task.Task;
import org.junit.Test;

import com.nic.activiti.springintegrated.AbsProcess;
import com.nic.activiti.springintegrated.ProcessHelper;

public class SimpleListenerProcess extends AbsProcess{
    

    @Override
    protected String getProcessDefinitionKey() {
        return "simpleListener";
    }

    @Test
    public void startProcess(){
        this.identityService.setAuthenticatedUserId("kermit");
        this.runtimeService.startProcessInstanceByKey(this.getProcessDefinitionKey());
    }
    
    @Test
    public void listRunningTask(){
        List<Task> tasks = this.taskService.createTaskQuery().processDefinitionKey(this.getProcessDefinitionKey() ).list();
        for(Task task : tasks){
            System.out.println(task.getId() + ", " + task.getAssignee());
        }
    }
    
    @Test
    public void stepForward(){
        String userId = "kermit";
        List<Task> tasks = ProcessHelper.taskService.createTaskQuery().processDefinitionKey(this.getProcessDefinitionKey()).list();
        for(Task task : tasks){
            ProcessHelper.taskService.claim(task.getId(), userId);
            ProcessHelper.taskService.complete(task.getId());
        }
        
    }

}
