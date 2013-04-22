package com.nic.activiti.springintegrated;

import java.util.List;

import org.activiti.engine.history.HistoricTaskInstance;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nic.activiti.springintegrated.process.GateWay01Process;

public class ProcessService {
    
    private String processDefinitionKey = "gateway01";

    public void printHistoryTasks(){
        ApplicationContext context = new ClassPathXmlApplicationContext("activiti-spring.cfg.xml");
        AbsProcess p= context.getBean(GateWay01Process.class);
        List<HistoricTaskInstance> tasks = p.historyService.createHistoricTaskInstanceQuery().list();
        for (HistoricTaskInstance task : tasks) {
            System.out.println(task.getId() + ", " + task.getName());
        }
    }
    
    @Test
    public void runFullProcess(){
        ProcessHelper.runFullProcess(this.processDefinitionKey);
    }
    
    @Test
    public void startProcessInstance(){
        String userId = "kermit";
        String processDefinitionId = "gateway01";
        ProcessHelper.startNewProcessInstance(userId, processDefinitionId);
    }

}
