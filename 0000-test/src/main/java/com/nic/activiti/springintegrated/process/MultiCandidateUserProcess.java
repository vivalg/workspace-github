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
import com.nic.activiti.springintegrated.service.WorkflowTaskService;

@ContextConfiguration(locations={"classpath:activiti-spring.cfg.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MultiCandidateUserProcess extends AbsProcess{

    @Autowired
    WorkflowTaskService workflowTaskService;
    
    @Test
    public void startProcess(){
        this.startNewProcess("kermit");
    }
    
    @Test
    public void stepForwardAll(){
        Map<String, Object> variables = new HashMap<String, Object>();
        List<Task> tasks = this.getRuntimeTasks();
        System.out.println(tasks.size());
        for(Task task : tasks){
            this.workflowTaskService.claimUserTask(task.getId(), "kermit");
            this.workflowTaskService.completeUserTask(task.getId(), variables);
        }
    }
    
    @Test
    public void test(){
        List<Task> tasks = this.taskService.createTaskQuery().taskCandidateUser("kermit").list();
        assert tasks.size() == 1;
        
        List<Task> tasks2 = this.taskService.createTaskQuery().taskCandidateUser("jack").list();
        assert tasks2.size() == 1;
        
        assert tasks.get(0).getId().equals(tasks2.get(0).getId());
        
    }
    
    @Override
    protected String getProcessDefinitionKey() {
        return "multiCandidateUserProcess";
    }
    
}
